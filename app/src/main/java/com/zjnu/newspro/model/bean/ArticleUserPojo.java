package com.zjnu.newspro.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * User--Hu mingzhi on 2018/3/16.
 * Created by ThinKPad
 */

public class ArticleUserPojo implements Serializable{

    private User user;
    private List<Article> articles;

    public ArticleUserPojo() {
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public ArticleUserPojo(User user, List<Article> articles) {

        this.user = user;
        this.articles = articles;
    }
    public static class User{
        private Long userId;

        private String userName;

        private String password;

        private String email;

        private String work;

        private Integer age;

        private Integer sex;

        private String location;

        private String avatar;

        private String phone;

        private Integer vip;

        private String introduction;

        public User() {
        }

        public User(Long userId, String userName, String password, String email, String work, Integer age, Integer sex, String location, String avatar, String phone, Integer vip, String introduction) {

            this.userId = userId;
            this.userName = userName;
            this.password = password;
            this.email = email;
            this.work = work;
            this.age = age;
            this.sex = sex;
            this.location = location;
            this.avatar = avatar;
            this.phone = phone;
            this.vip = vip;
            this.introduction = introduction;
        }

        public Long getUserId() {

            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getWork() {
            return work;
        }

        public void setWork(String work) {
            this.work = work;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Integer getSex() {
            return sex;
        }

        public void setSex(Integer sex) {
            this.sex = sex;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Integer getVip() {
            return vip;
        }

        public void setVip(Integer vip) {
            this.vip = vip;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }
    }
    public static class Article{
        private Long articleId;

        private String articleContent;

        private String articleTitle;

        private String kindParentName;

        private String kindChildName;

        private Long userId;

        private String articleSummary;

        private String articleWriter;

        private String articleSource;

        private Integer articleHints;

        private String releaseTime;

        private String articlePower;

        private Integer checkUp;

        private Integer articleCollection;

        private Integer articleUp;

        private Integer articleDown;

        private String articleState;

        private String articleAvatar;

        private Integer articleBig;

        private Integer articleFirst;

        private String sourceUrl;

        public Article() {
        }

        public Article(Long articleId, String articleContent, String articleTitle, String kindParentName, String kindChildName, Long userId, String articleSummary, String articleWriter, String articleSource, Integer articleHints, String releaseTime, String articlePower, Integer checkUp, Integer articleCollection, Integer articleUp, Integer articleDown, String articleState, String articleAvatar, Integer articleBig, Integer articleFirst, String sourceUrl) {

            this.articleId = articleId;
            this.articleContent = articleContent;
            this.articleTitle = articleTitle;
            this.kindParentName = kindParentName;
            this.kindChildName = kindChildName;
            this.userId = userId;
            this.articleSummary = articleSummary;
            this.articleWriter = articleWriter;
            this.articleSource = articleSource;
            this.articleHints = articleHints;
            this.releaseTime = releaseTime;
            this.articlePower = articlePower;
            this.checkUp = checkUp;
            this.articleCollection = articleCollection;
            this.articleUp = articleUp;
            this.articleDown = articleDown;
            this.articleState = articleState;
            this.articleAvatar = articleAvatar;
            this.articleBig = articleBig;
            this.articleFirst = articleFirst;
            this.sourceUrl = sourceUrl;
        }

        public Long getArticleId() {

            return articleId;
        }

        public void setArticleId(Long articleId) {
            this.articleId = articleId;
        }

        public String getArticleContent() {
            return articleContent;
        }

        public void setArticleContent(String articleContent) {
            this.articleContent = articleContent;
        }

        public String getArticleTitle() {
            return articleTitle;
        }

        public void setArticleTitle(String articleTitle) {
            this.articleTitle = articleTitle;
        }

        public String getKindParentName() {
            return kindParentName;
        }

        public void setKindParentName(String kindParentName) {
            this.kindParentName = kindParentName;
        }

        public String getKindChildName() {
            return kindChildName;
        }

        public void setKindChildName(String kindChildName) {
            this.kindChildName = kindChildName;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getArticleSummary() {
            return articleSummary;
        }

        public void setArticleSummary(String articleSummary) {
            this.articleSummary = articleSummary;
        }

        public String getArticleWriter() {
            return articleWriter;
        }

        public void setArticleWriter(String articleWriter) {
            this.articleWriter = articleWriter;
        }

        public String getArticleSource() {
            return articleSource;
        }

        public void setArticleSource(String articleSource) {
            this.articleSource = articleSource;
        }

        public Integer getArticleHints() {
            return articleHints;
        }

        public void setArticleHints(Integer articleHints) {
            this.articleHints = articleHints;
        }

        public String getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(String releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getArticlePower() {
            return articlePower;
        }

        public void setArticlePower(String articlePower) {
            this.articlePower = articlePower;
        }

        public Integer getCheckUp() {
            return checkUp;
        }

        public void setCheckUp(Integer checkUp) {
            this.checkUp = checkUp;
        }

        public Integer getArticleCollection() {
            return articleCollection;
        }

        public void setArticleCollection(Integer articleCollection) {
            this.articleCollection = articleCollection;
        }

        public Integer getArticleUp() {
            return articleUp;
        }

        public void setArticleUp(Integer articleUp) {
            this.articleUp = articleUp;
        }

        public Integer getArticleDown() {
            return articleDown;
        }

        public void setArticleDown(Integer articleDown) {
            this.articleDown = articleDown;
        }

        public String getArticleState() {
            return articleState;
        }

        public void setArticleState(String articleState) {
            this.articleState = articleState;
        }

        public String getArticleAvatar() {
            return articleAvatar;
        }

        public void setArticleAvatar(String articleAvatar) {
            this.articleAvatar = articleAvatar;
        }

        public Integer getArticleBig() {
            return articleBig;
        }

        public void setArticleBig(Integer articleBig) {
            this.articleBig = articleBig;
        }

        public Integer getArticleFirst() {
            return articleFirst;
        }

        public void setArticleFirst(Integer articleFirst) {
            this.articleFirst = articleFirst;
        }

        public String getSourceUrl() {
            return sourceUrl;
        }

        public void setSourceUrl(String sourceUrl) {
            this.sourceUrl = sourceUrl;
        }
    }
}
