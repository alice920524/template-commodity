package com.duia.commodity.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.duia.commodity.common.Constant;
import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.CetPromotionMapper;
import com.duia.commodity.model.CetPromotion;
import com.duia.commodity.service.CetPromotionService;
import com.duia.commodity.service.WxService;
import com.duia.constant.Constants;
import com.duia.enums.Status;
import com.github.binarywang.java.emoji.EmojiConverter;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.wxpay.lib.WxPayException;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * Created by CodeGenerator on 2018/05/30.
 */
@Service
@Transactional
public class CetPromotionServiceImpl extends AbstractService<CetPromotion> implements CetPromotionService {

    public static final Integer EFFECTIVE = Status.VALID.getState();
    public static final Integer INVALID = Status.INVALID.getState();
    private EmojiConverter emojiConverter = EmojiConverter.getInstance();
    public static String[] randomNames = new String[]{"{name:扫地僧,school:麻省理工大学}", "{name:张三丰,school:剑桥大学}", "{name:灭绝师太,school:斯坦福大学}", "{name:飞云子,school:牛津大学}",
            "{name:余沧海,school:哥伦比亚大学}", "{name:浮尘子,school:芝加哥大学}", "{name:青灵子,school:耶鲁大学}", "{name:王重阳,school:华盛顿大学}", "{name:风清扬,school:多伦多大学}", "{name:无崖子,school:哥本哈根大学}"};
    @Resource
    private CetPromotionMapper cetPromotionMapper;
    @Resource
    private WxService wxService;
    @Value("${item.domain}")
    private String itemDomain;

    @Override
    public CetPromotion query(String openid, Integer appType) {
        CetPromotion promotion = this.findCetPromotionByOpenid(openid, appType);
        promotion.setNickName(emojiConverter.toUnicode(promotion.getNickName()));
        return promotion;
    }

    @Override
    public void count(Integer countType, String openid, Integer appType) {
        CetPromotion cetPromotion = this.findCetPromotionByOpenid(openid, appType);
        switch (countType) {
            case 1:
                if (!EFFECTIVE.equals(cetPromotion.getIsFirst())) {
                    cetPromotion.setIsFirst(EFFECTIVE);
                }
                break;
            case 2:
                if (!EFFECTIVE.equals(cetPromotion.getIsAgain())) {
                    cetPromotion.setIsAgain(EFFECTIVE);
                }
                break;
            case 3:
                if (!EFFECTIVE.equals(cetPromotion.getIsMaking())) {
                    cetPromotion.setIsMaking(EFFECTIVE);
                }
                break;
            case 4:
                if (!EFFECTIVE.equals(cetPromotion.getIsFinish())) {
                    cetPromotion.setIsFinish(EFFECTIVE);
                }
                break;
            case 5:
                if (!EFFECTIVE.equals(cetPromotion.getIsShare())) {
                    cetPromotion.setIsShare(EFFECTIVE);
                }
                break;
            case 6:
                if (!EFFECTIVE.equals(cetPromotion.getClickEgg())) {
                    cetPromotion.setClickEgg(EFFECTIVE);
                }
                break;
            case 7:
                if (!EFFECTIVE.equals(cetPromotion.getClickBuy())) {
                    cetPromotion.setClickBuy(EFFECTIVE);
                }
                break;
            default:
                break;
        }
        cetPromotionMapper.updateByPrimaryKeySelective(cetPromotion);
    }

    @Override
    public void saveCetPromotion(CetPromotion cetPromotion, String accessToken) throws IOException, WxPayException {
        if (Objects.equals(cetPromotion.getOpenid(), null)) {
            return;
        }
        cetPromotion.setOptime(new Date());
        CetPromotion cet = this.findCetPromotionByOpenid(cetPromotion.getOpenid(), cetPromotion.getAppType());
        if (cet != null && !StringUtils.isEmpty(cet.getId())) {
            cet.setId(cet.getId());
            cet.setIsAgain(EFFECTIVE);
            cetPromotionMapper.updateByPrimaryKeySelective(cet);
        } else {
            String randomName = randomNames[RandomUtils.nextInt(0, randomNames.length)];
            JSONObject jsonObject = new Gson().fromJson(randomName, JSONObject.class);
            if (Constant.APP_TYPE_WX_GZH.equals(cetPromotion.getAppType())) {
                //获取英语四级公众号信息
                Map token = wxService.getAuthorizedUser(cetPromotion.getOpenid(), accessToken);
                String alias = emojiConverter.toAlias(String.valueOf(token.get("nickname")));
                cetPromotion.setNickName(alias);
                cetPromotion.setImgUrl(String.valueOf(token.get("headimgurl")));
                cetPromotion.setSchool(jsonObject.getString("school"));
            } else {
                String imgUrl = Constants.HTTPS + itemDomain + "/image/cet_header_img.png";
                cetPromotion.setImgUrl(imgUrl);
                cetPromotion.setNickName(jsonObject.getString("name"));
                cetPromotion.setSchool(jsonObject.getString("school"));
            }
            cetPromotion.setIsFirst(EFFECTIVE);
            cetPromotion.setIsAgain(INVALID);
            cetPromotion.setIsMaking(INVALID);
            cetPromotion.setIsFinish(INVALID);
            cetPromotion.setIsShare(INVALID);
            cetPromotion.setClickEgg(INVALID);
            cetPromotion.setClickBuy(INVALID);
            cetPromotionMapper.insert(cetPromotion);
        }
    }

    @Override
    public void updateScore(CetPromotion cetPromotion) {
        CetPromotion cet = this.findCetPromotionByOpenid(cetPromotion.getOpenid(), cetPromotion.getAppType());
        if (cet != null && !StringUtils.isEmpty(cet.getId())) {
            cetPromotion.setOptime(new Date());
            cetPromotion.setId(cet.getId());
            //更新保存分数
            Float f = 0.5f;
            DecimalFormat df = new DecimalFormat("#.0");
            Float lscore = Float.valueOf(df.format(RandomUtils.nextInt(120, 248) + f));
            Float rscore = Float.valueOf(df.format(RandomUtils.nextInt(135, 248) + f));
            Float tscore = Float.valueOf(df.format(RandomUtils.nextInt(90, 106) + f));
            Float cscore = Float.valueOf(df.format(RandomUtils.nextInt(80, 106) + f));
            cetPromotion.setListeningScore(lscore);
            cetPromotion.setReadingScore(rscore);
            cetPromotion.setTranslateScore(tscore);
            cetPromotion.setClozeScore(cscore);
            Float total = lscore + rscore + tscore + cscore;
            cetPromotion.setTotalScore(total);
            cetPromotionMapper.updateByPrimaryKeySelective(cetPromotion);
        }
    }

    @Override
    public Map getAuthorizedUser(String accessToken, String openid, Integer appType) throws WxPayException {
        Map dataMap = Maps.newHashMap();
        if (appType.equals(0)) {
            dataMap = wxService.getAuthorizedUser(openid, accessToken);
        }
        return dataMap;
    }

    private CetPromotion findCetPromotionByOpenid(String openid, Integer appType) {
        if (openid != null) {
            Condition condition = new Condition(CetPromotion.class);
            condition.createCriteria().andEqualTo("openid", openid)
                    .andEqualTo("appType", appType);
            List<CetPromotion> cetPromotionList = cetPromotionMapper.selectByCondition(condition);
            if (!CollectionUtils.isEmpty(cetPromotionList)) {
                return cetPromotionList.get(0);
            }
        }
        return null;
    }
}
