package edu.hdu.lab.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FamilyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FamilyExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterion("time not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTextsIsNull() {
            addCriterion("texts is null");
            return (Criteria) this;
        }

        public Criteria andTextsIsNotNull() {
            addCriterion("texts is not null");
            return (Criteria) this;
        }

        public Criteria andTextsEqualTo(String value) {
            addCriterion("texts =", value, "texts");
            return (Criteria) this;
        }

        public Criteria andTextsNotEqualTo(String value) {
            addCriterion("texts <>", value, "texts");
            return (Criteria) this;
        }

        public Criteria andTextsGreaterThan(String value) {
            addCriterion("texts >", value, "texts");
            return (Criteria) this;
        }

        public Criteria andTextsGreaterThanOrEqualTo(String value) {
            addCriterion("texts >=", value, "texts");
            return (Criteria) this;
        }

        public Criteria andTextsLessThan(String value) {
            addCriterion("texts <", value, "texts");
            return (Criteria) this;
        }

        public Criteria andTextsLessThanOrEqualTo(String value) {
            addCriterion("texts <=", value, "texts");
            return (Criteria) this;
        }

        public Criteria andTextsLike(String value) {
            addCriterion("texts like", value, "texts");
            return (Criteria) this;
        }

        public Criteria andTextsNotLike(String value) {
            addCriterion("texts not like", value, "texts");
            return (Criteria) this;
        }

        public Criteria andTextsIn(List<String> values) {
            addCriterion("texts in", values, "texts");
            return (Criteria) this;
        }

        public Criteria andTextsNotIn(List<String> values) {
            addCriterion("texts not in", values, "texts");
            return (Criteria) this;
        }

        public Criteria andTextsBetween(String value1, String value2) {
            addCriterion("texts between", value1, value2, "texts");
            return (Criteria) this;
        }

        public Criteria andTextsNotBetween(String value1, String value2) {
            addCriterion("texts not between", value1, value2, "texts");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(Integer value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(Integer value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(Integer value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(Integer value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(Integer value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(Integer value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<Integer> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<Integer> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(Integer value1, Integer value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(Integer value1, Integer value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andLastEditorIsNull() {
            addCriterion("last_editor is null");
            return (Criteria) this;
        }

        public Criteria andLastEditorIsNotNull() {
            addCriterion("last_editor is not null");
            return (Criteria) this;
        }

        public Criteria andLastEditorEqualTo(Integer value) {
            addCriterion("last_editor =", value, "lastEditor");
            return (Criteria) this;
        }

        public Criteria andLastEditorNotEqualTo(Integer value) {
            addCriterion("last_editor <>", value, "lastEditor");
            return (Criteria) this;
        }

        public Criteria andLastEditorGreaterThan(Integer value) {
            addCriterion("last_editor >", value, "lastEditor");
            return (Criteria) this;
        }

        public Criteria andLastEditorGreaterThanOrEqualTo(Integer value) {
            addCriterion("last_editor >=", value, "lastEditor");
            return (Criteria) this;
        }

        public Criteria andLastEditorLessThan(Integer value) {
            addCriterion("last_editor <", value, "lastEditor");
            return (Criteria) this;
        }

        public Criteria andLastEditorLessThanOrEqualTo(Integer value) {
            addCriterion("last_editor <=", value, "lastEditor");
            return (Criteria) this;
        }

        public Criteria andLastEditorIn(List<Integer> values) {
            addCriterion("last_editor in", values, "lastEditor");
            return (Criteria) this;
        }

        public Criteria andLastEditorNotIn(List<Integer> values) {
            addCriterion("last_editor not in", values, "lastEditor");
            return (Criteria) this;
        }

        public Criteria andLastEditorBetween(Integer value1, Integer value2) {
            addCriterion("last_editor between", value1, value2, "lastEditor");
            return (Criteria) this;
        }

        public Criteria andLastEditorNotBetween(Integer value1, Integer value2) {
            addCriterion("last_editor not between", value1, value2, "lastEditor");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeIsNull() {
            addCriterion("last_edit_time is null");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeIsNotNull() {
            addCriterion("last_edit_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeEqualTo(Date value) {
            addCriterion("last_edit_time =", value, "lastEditTime");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeNotEqualTo(Date value) {
            addCriterion("last_edit_time <>", value, "lastEditTime");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeGreaterThan(Date value) {
            addCriterion("last_edit_time >", value, "lastEditTime");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_edit_time >=", value, "lastEditTime");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeLessThan(Date value) {
            addCriterion("last_edit_time <", value, "lastEditTime");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_edit_time <=", value, "lastEditTime");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeIn(List<Date> values) {
            addCriterion("last_edit_time in", values, "lastEditTime");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeNotIn(List<Date> values) {
            addCriterion("last_edit_time not in", values, "lastEditTime");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeBetween(Date value1, Date value2) {
            addCriterion("last_edit_time between", value1, value2, "lastEditTime");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_edit_time not between", value1, value2, "lastEditTime");
            return (Criteria) this;
        }

        public Criteria andPictureOneIsNull() {
            addCriterion("picture_one is null");
            return (Criteria) this;
        }

        public Criteria andPictureOneIsNotNull() {
            addCriterion("picture_one is not null");
            return (Criteria) this;
        }

        public Criteria andPictureOneEqualTo(String value) {
            addCriterion("picture_one =", value, "pictureOne");
            return (Criteria) this;
        }

        public Criteria andPictureOneNotEqualTo(String value) {
            addCriterion("picture_one <>", value, "pictureOne");
            return (Criteria) this;
        }

        public Criteria andPictureOneGreaterThan(String value) {
            addCriterion("picture_one >", value, "pictureOne");
            return (Criteria) this;
        }

        public Criteria andPictureOneGreaterThanOrEqualTo(String value) {
            addCriterion("picture_one >=", value, "pictureOne");
            return (Criteria) this;
        }

        public Criteria andPictureOneLessThan(String value) {
            addCriterion("picture_one <", value, "pictureOne");
            return (Criteria) this;
        }

        public Criteria andPictureOneLessThanOrEqualTo(String value) {
            addCriterion("picture_one <=", value, "pictureOne");
            return (Criteria) this;
        }

        public Criteria andPictureOneLike(String value) {
            addCriterion("picture_one like", value, "pictureOne");
            return (Criteria) this;
        }

        public Criteria andPictureOneNotLike(String value) {
            addCriterion("picture_one not like", value, "pictureOne");
            return (Criteria) this;
        }

        public Criteria andPictureOneIn(List<String> values) {
            addCriterion("picture_one in", values, "pictureOne");
            return (Criteria) this;
        }

        public Criteria andPictureOneNotIn(List<String> values) {
            addCriterion("picture_one not in", values, "pictureOne");
            return (Criteria) this;
        }

        public Criteria andPictureOneBetween(String value1, String value2) {
            addCriterion("picture_one between", value1, value2, "pictureOne");
            return (Criteria) this;
        }

        public Criteria andPictureOneNotBetween(String value1, String value2) {
            addCriterion("picture_one not between", value1, value2, "pictureOne");
            return (Criteria) this;
        }

        public Criteria andPictureTwoIsNull() {
            addCriterion("picture_two is null");
            return (Criteria) this;
        }

        public Criteria andPictureTwoIsNotNull() {
            addCriterion("picture_two is not null");
            return (Criteria) this;
        }

        public Criteria andPictureTwoEqualTo(String value) {
            addCriterion("picture_two =", value, "pictureTwo");
            return (Criteria) this;
        }

        public Criteria andPictureTwoNotEqualTo(String value) {
            addCriterion("picture_two <>", value, "pictureTwo");
            return (Criteria) this;
        }

        public Criteria andPictureTwoGreaterThan(String value) {
            addCriterion("picture_two >", value, "pictureTwo");
            return (Criteria) this;
        }

        public Criteria andPictureTwoGreaterThanOrEqualTo(String value) {
            addCriterion("picture_two >=", value, "pictureTwo");
            return (Criteria) this;
        }

        public Criteria andPictureTwoLessThan(String value) {
            addCriterion("picture_two <", value, "pictureTwo");
            return (Criteria) this;
        }

        public Criteria andPictureTwoLessThanOrEqualTo(String value) {
            addCriterion("picture_two <=", value, "pictureTwo");
            return (Criteria) this;
        }

        public Criteria andPictureTwoLike(String value) {
            addCriterion("picture_two like", value, "pictureTwo");
            return (Criteria) this;
        }

        public Criteria andPictureTwoNotLike(String value) {
            addCriterion("picture_two not like", value, "pictureTwo");
            return (Criteria) this;
        }

        public Criteria andPictureTwoIn(List<String> values) {
            addCriterion("picture_two in", values, "pictureTwo");
            return (Criteria) this;
        }

        public Criteria andPictureTwoNotIn(List<String> values) {
            addCriterion("picture_two not in", values, "pictureTwo");
            return (Criteria) this;
        }

        public Criteria andPictureTwoBetween(String value1, String value2) {
            addCriterion("picture_two between", value1, value2, "pictureTwo");
            return (Criteria) this;
        }

        public Criteria andPictureTwoNotBetween(String value1, String value2) {
            addCriterion("picture_two not between", value1, value2, "pictureTwo");
            return (Criteria) this;
        }

        public Criteria andPictureThreeIsNull() {
            addCriterion("picture_three is null");
            return (Criteria) this;
        }

        public Criteria andPictureThreeIsNotNull() {
            addCriterion("picture_three is not null");
            return (Criteria) this;
        }

        public Criteria andPictureThreeEqualTo(String value) {
            addCriterion("picture_three =", value, "pictureThree");
            return (Criteria) this;
        }

        public Criteria andPictureThreeNotEqualTo(String value) {
            addCriterion("picture_three <>", value, "pictureThree");
            return (Criteria) this;
        }

        public Criteria andPictureThreeGreaterThan(String value) {
            addCriterion("picture_three >", value, "pictureThree");
            return (Criteria) this;
        }

        public Criteria andPictureThreeGreaterThanOrEqualTo(String value) {
            addCriterion("picture_three >=", value, "pictureThree");
            return (Criteria) this;
        }

        public Criteria andPictureThreeLessThan(String value) {
            addCriterion("picture_three <", value, "pictureThree");
            return (Criteria) this;
        }

        public Criteria andPictureThreeLessThanOrEqualTo(String value) {
            addCriterion("picture_three <=", value, "pictureThree");
            return (Criteria) this;
        }

        public Criteria andPictureThreeLike(String value) {
            addCriterion("picture_three like", value, "pictureThree");
            return (Criteria) this;
        }

        public Criteria andPictureThreeNotLike(String value) {
            addCriterion("picture_three not like", value, "pictureThree");
            return (Criteria) this;
        }

        public Criteria andPictureThreeIn(List<String> values) {
            addCriterion("picture_three in", values, "pictureThree");
            return (Criteria) this;
        }

        public Criteria andPictureThreeNotIn(List<String> values) {
            addCriterion("picture_three not in", values, "pictureThree");
            return (Criteria) this;
        }

        public Criteria andPictureThreeBetween(String value1, String value2) {
            addCriterion("picture_three between", value1, value2, "pictureThree");
            return (Criteria) this;
        }

        public Criteria andPictureThreeNotBetween(String value1, String value2) {
            addCriterion("picture_three not between", value1, value2, "pictureThree");
            return (Criteria) this;
        }

        public Criteria andPopularityIsNull() {
            addCriterion("popularity is null");
            return (Criteria) this;
        }

        public Criteria andPopularityIsNotNull() {
            addCriterion("popularity is not null");
            return (Criteria) this;
        }

        public Criteria andPopularityEqualTo(Integer value) {
            addCriterion("popularity =", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityNotEqualTo(Integer value) {
            addCriterion("popularity <>", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityGreaterThan(Integer value) {
            addCriterion("popularity >", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityGreaterThanOrEqualTo(Integer value) {
            addCriterion("popularity >=", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityLessThan(Integer value) {
            addCriterion("popularity <", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityLessThanOrEqualTo(Integer value) {
            addCriterion("popularity <=", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityIn(List<Integer> values) {
            addCriterion("popularity in", values, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityNotIn(List<Integer> values) {
            addCriterion("popularity not in", values, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityBetween(Integer value1, Integer value2) {
            addCriterion("popularity between", value1, value2, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityNotBetween(Integer value1, Integer value2) {
            addCriterion("popularity not between", value1, value2, "popularity");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}