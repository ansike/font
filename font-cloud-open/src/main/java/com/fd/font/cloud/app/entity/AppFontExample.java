package com.fd.font.cloud.app.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AppFontExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AppFontExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andAppFontIdIsNull() {
            addCriterion("app_font_id is null");
            return (Criteria) this;
        }

        public Criteria andAppFontIdIsNotNull() {
            addCriterion("app_font_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppFontIdEqualTo(Integer value) {
            addCriterion("app_font_id =", value, "appFontId");
            return (Criteria) this;
        }

        public Criteria andAppFontIdNotEqualTo(Integer value) {
            addCriterion("app_font_id <>", value, "appFontId");
            return (Criteria) this;
        }

        public Criteria andAppFontIdGreaterThan(Integer value) {
            addCriterion("app_font_id >", value, "appFontId");
            return (Criteria) this;
        }

        public Criteria andAppFontIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("app_font_id >=", value, "appFontId");
            return (Criteria) this;
        }

        public Criteria andAppFontIdLessThan(Integer value) {
            addCriterion("app_font_id <", value, "appFontId");
            return (Criteria) this;
        }

        public Criteria andAppFontIdLessThanOrEqualTo(Integer value) {
            addCriterion("app_font_id <=", value, "appFontId");
            return (Criteria) this;
        }

        public Criteria andAppFontIdIn(List<Integer> values) {
            addCriterion("app_font_id in", values, "appFontId");
            return (Criteria) this;
        }

        public Criteria andAppFontIdNotIn(List<Integer> values) {
            addCriterion("app_font_id not in", values, "appFontId");
            return (Criteria) this;
        }

        public Criteria andAppFontIdBetween(Integer value1, Integer value2) {
            addCriterion("app_font_id between", value1, value2, "appFontId");
            return (Criteria) this;
        }

        public Criteria andAppFontIdNotBetween(Integer value1, Integer value2) {
            addCriterion("app_font_id not between", value1, value2, "appFontId");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNull() {
            addCriterion("app_id is null");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNotNull() {
            addCriterion("app_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppIdEqualTo(Integer value) {
            addCriterion("app_id =", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotEqualTo(Integer value) {
            addCriterion("app_id <>", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThan(Integer value) {
            addCriterion("app_id >", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("app_id >=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThan(Integer value) {
            addCriterion("app_id <", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThanOrEqualTo(Integer value) {
            addCriterion("app_id <=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdIn(List<Integer> values) {
            addCriterion("app_id in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotIn(List<Integer> values) {
            addCriterion("app_id not in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdBetween(Integer value1, Integer value2) {
            addCriterion("app_id between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotBetween(Integer value1, Integer value2) {
            addCriterion("app_id not between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andFontIdIsNull() {
            addCriterion("font_id is null");
            return (Criteria) this;
        }

        public Criteria andFontIdIsNotNull() {
            addCriterion("font_id is not null");
            return (Criteria) this;
        }

        public Criteria andFontIdEqualTo(Integer value) {
            addCriterion("font_id =", value, "fontId");
            return (Criteria) this;
        }

        public Criteria andFontIdNotEqualTo(Integer value) {
            addCriterion("font_id <>", value, "fontId");
            return (Criteria) this;
        }

        public Criteria andFontIdGreaterThan(Integer value) {
            addCriterion("font_id >", value, "fontId");
            return (Criteria) this;
        }

        public Criteria andFontIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("font_id >=", value, "fontId");
            return (Criteria) this;
        }

        public Criteria andFontIdLessThan(Integer value) {
            addCriterion("font_id <", value, "fontId");
            return (Criteria) this;
        }

        public Criteria andFontIdLessThanOrEqualTo(Integer value) {
            addCriterion("font_id <=", value, "fontId");
            return (Criteria) this;
        }

        public Criteria andFontIdIn(List<Integer> values) {
            addCriterion("font_id in", values, "fontId");
            return (Criteria) this;
        }

        public Criteria andFontIdNotIn(List<Integer> values) {
            addCriterion("font_id not in", values, "fontId");
            return (Criteria) this;
        }

        public Criteria andFontIdBetween(Integer value1, Integer value2) {
            addCriterion("font_id between", value1, value2, "fontId");
            return (Criteria) this;
        }

        public Criteria andFontIdNotBetween(Integer value1, Integer value2) {
            addCriterion("font_id not between", value1, value2, "fontId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andAuthzDateIsNull() {
            addCriterion("authz_date is null");
            return (Criteria) this;
        }

        public Criteria andAuthzDateIsNotNull() {
            addCriterion("authz_date is not null");
            return (Criteria) this;
        }

        public Criteria andAuthzDateEqualTo(Date value) {
            addCriterionForJDBCDate("authz_date =", value, "authzDate");
            return (Criteria) this;
        }

        public Criteria andAuthzDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("authz_date <>", value, "authzDate");
            return (Criteria) this;
        }

        public Criteria andAuthzDateGreaterThan(Date value) {
            addCriterionForJDBCDate("authz_date >", value, "authzDate");
            return (Criteria) this;
        }

        public Criteria andAuthzDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("authz_date >=", value, "authzDate");
            return (Criteria) this;
        }

        public Criteria andAuthzDateLessThan(Date value) {
            addCriterionForJDBCDate("authz_date <", value, "authzDate");
            return (Criteria) this;
        }

        public Criteria andAuthzDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("authz_date <=", value, "authzDate");
            return (Criteria) this;
        }

        public Criteria andAuthzDateIn(List<Date> values) {
            addCriterionForJDBCDate("authz_date in", values, "authzDate");
            return (Criteria) this;
        }

        public Criteria andAuthzDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("authz_date not in", values, "authzDate");
            return (Criteria) this;
        }

        public Criteria andAuthzDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("authz_date between", value1, value2, "authzDate");
            return (Criteria) this;
        }

        public Criteria andAuthzDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("authz_date not between", value1, value2, "authzDate");
            return (Criteria) this;
        }

        public Criteria andUnauthzDateIsNull() {
            addCriterion("unauthz_date is null");
            return (Criteria) this;
        }

        public Criteria andUnauthzDateIsNotNull() {
            addCriterion("unauthz_date is not null");
            return (Criteria) this;
        }

        public Criteria andUnauthzDateEqualTo(Date value) {
            addCriterionForJDBCDate("unauthz_date =", value, "unauthzDate");
            return (Criteria) this;
        }

        public Criteria andUnauthzDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("unauthz_date <>", value, "unauthzDate");
            return (Criteria) this;
        }

        public Criteria andUnauthzDateGreaterThan(Date value) {
            addCriterionForJDBCDate("unauthz_date >", value, "unauthzDate");
            return (Criteria) this;
        }

        public Criteria andUnauthzDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("unauthz_date >=", value, "unauthzDate");
            return (Criteria) this;
        }

        public Criteria andUnauthzDateLessThan(Date value) {
            addCriterionForJDBCDate("unauthz_date <", value, "unauthzDate");
            return (Criteria) this;
        }

        public Criteria andUnauthzDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("unauthz_date <=", value, "unauthzDate");
            return (Criteria) this;
        }

        public Criteria andUnauthzDateIn(List<Date> values) {
            addCriterionForJDBCDate("unauthz_date in", values, "unauthzDate");
            return (Criteria) this;
        }

        public Criteria andUnauthzDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("unauthz_date not in", values, "unauthzDate");
            return (Criteria) this;
        }

        public Criteria andUnauthzDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("unauthz_date between", value1, value2, "unauthzDate");
            return (Criteria) this;
        }

        public Criteria andUnauthzDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("unauthz_date not between", value1, value2, "unauthzDate");
            return (Criteria) this;
        }

        public Criteria andAppFontPicIsNull() {
            addCriterion("app_font_pic is null");
            return (Criteria) this;
        }

        public Criteria andAppFontPicIsNotNull() {
            addCriterion("app_font_pic is not null");
            return (Criteria) this;
        }

        public Criteria andAppFontPicEqualTo(String value) {
            addCriterion("app_font_pic =", value, "appFontPic");
            return (Criteria) this;
        }

        public Criteria andAppFontPicNotEqualTo(String value) {
            addCriterion("app_font_pic <>", value, "appFontPic");
            return (Criteria) this;
        }

        public Criteria andAppFontPicGreaterThan(String value) {
            addCriterion("app_font_pic >", value, "appFontPic");
            return (Criteria) this;
        }

        public Criteria andAppFontPicGreaterThanOrEqualTo(String value) {
            addCriterion("app_font_pic >=", value, "appFontPic");
            return (Criteria) this;
        }

        public Criteria andAppFontPicLessThan(String value) {
            addCriterion("app_font_pic <", value, "appFontPic");
            return (Criteria) this;
        }

        public Criteria andAppFontPicLessThanOrEqualTo(String value) {
            addCriterion("app_font_pic <=", value, "appFontPic");
            return (Criteria) this;
        }

        public Criteria andAppFontPicLike(String value) {
            addCriterion("app_font_pic like", value, "appFontPic");
            return (Criteria) this;
        }

        public Criteria andAppFontPicNotLike(String value) {
            addCriterion("app_font_pic not like", value, "appFontPic");
            return (Criteria) this;
        }

        public Criteria andAppFontPicIn(List<String> values) {
            addCriterion("app_font_pic in", values, "appFontPic");
            return (Criteria) this;
        }

        public Criteria andAppFontPicNotIn(List<String> values) {
            addCriterion("app_font_pic not in", values, "appFontPic");
            return (Criteria) this;
        }

        public Criteria andAppFontPicBetween(String value1, String value2) {
            addCriterion("app_font_pic between", value1, value2, "appFontPic");
            return (Criteria) this;
        }

        public Criteria andAppFontPicNotBetween(String value1, String value2) {
            addCriterion("app_font_pic not between", value1, value2, "appFontPic");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(Integer value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(Integer value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(Integer value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(Integer value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(Integer value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<Integer> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<Integer> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(Integer value1, Integer value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(Integer value1, Integer value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(Integer value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(Integer value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(Integer value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(Integer value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(Integer value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(Integer value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<Integer> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<Integer> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(Integer value1, Integer value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(Integer value1, Integer value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNull() {
            addCriterion("is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(Byte value) {
            addCriterion("is_deleted =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(Byte value) {
            addCriterion("is_deleted <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(Byte value) {
            addCriterion("is_deleted >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_deleted >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(Byte value) {
            addCriterion("is_deleted <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(Byte value) {
            addCriterion("is_deleted <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<Byte> values) {
            addCriterion("is_deleted in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<Byte> values) {
            addCriterion("is_deleted not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(Byte value1, Byte value2) {
            addCriterion("is_deleted between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(Byte value1, Byte value2) {
            addCriterion("is_deleted not between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andFontVersionIdIsNull() {
            addCriterion("font_version_id is null");
            return (Criteria) this;
        }

        public Criteria andFontVersionIdIsNotNull() {
            addCriterion("font_version_id is not null");
            return (Criteria) this;
        }

        public Criteria andFontVersionIdEqualTo(Integer value) {
            addCriterion("font_version_id =", value, "fontVersionId");
            return (Criteria) this;
        }

        public Criteria andFontVersionIdNotEqualTo(Integer value) {
            addCriterion("font_version_id <>", value, "fontVersionId");
            return (Criteria) this;
        }

        public Criteria andFontVersionIdGreaterThan(Integer value) {
            addCriterion("font_version_id >", value, "fontVersionId");
            return (Criteria) this;
        }

        public Criteria andFontVersionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("font_version_id >=", value, "fontVersionId");
            return (Criteria) this;
        }

        public Criteria andFontVersionIdLessThan(Integer value) {
            addCriterion("font_version_id <", value, "fontVersionId");
            return (Criteria) this;
        }

        public Criteria andFontVersionIdLessThanOrEqualTo(Integer value) {
            addCriterion("font_version_id <=", value, "fontVersionId");
            return (Criteria) this;
        }

        public Criteria andFontVersionIdIn(List<Integer> values) {
            addCriterion("font_version_id in", values, "fontVersionId");
            return (Criteria) this;
        }

        public Criteria andFontVersionIdNotIn(List<Integer> values) {
            addCriterion("font_version_id not in", values, "fontVersionId");
            return (Criteria) this;
        }

        public Criteria andFontVersionIdBetween(Integer value1, Integer value2) {
            addCriterion("font_version_id between", value1, value2, "fontVersionId");
            return (Criteria) this;
        }

        public Criteria andFontVersionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("font_version_id not between", value1, value2, "fontVersionId");
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