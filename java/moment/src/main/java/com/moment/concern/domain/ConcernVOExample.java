package com.moment.concern.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConcernVOExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public ConcernVOExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria implements Serializable {
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

        public Criteria andWatchuseridIsNull() {
            addCriterion("watchuserId is null");
            return (Criteria) this;
        }

        public Criteria andWatchuseridIsNotNull() {
            addCriterion("watchuserId is not null");
            return (Criteria) this;
        }

        public Criteria andWatchuseridEqualTo(Integer value) {
            addCriterion("watchuserId =", value, "watchuserid");
            return (Criteria) this;
        }

        public Criteria andWatchuseridNotEqualTo(Integer value) {
            addCriterion("watchuserId <>", value, "watchuserid");
            return (Criteria) this;
        }

        public Criteria andWatchuseridGreaterThan(Integer value) {
            addCriterion("watchuserId >", value, "watchuserid");
            return (Criteria) this;
        }

        public Criteria andWatchuseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("watchuserId >=", value, "watchuserid");
            return (Criteria) this;
        }

        public Criteria andWatchuseridLessThan(Integer value) {
            addCriterion("watchuserId <", value, "watchuserid");
            return (Criteria) this;
        }

        public Criteria andWatchuseridLessThanOrEqualTo(Integer value) {
            addCriterion("watchuserId <=", value, "watchuserid");
            return (Criteria) this;
        }

        public Criteria andWatchuseridIn(List<Integer> values) {
            addCriterion("watchuserId in", values, "watchuserid");
            return (Criteria) this;
        }

        public Criteria andWatchuseridNotIn(List<Integer> values) {
            addCriterion("watchuserId not in", values, "watchuserid");
            return (Criteria) this;
        }

        public Criteria andWatchuseridBetween(Integer value1, Integer value2) {
            addCriterion("watchuserId between", value1, value2, "watchuserid");
            return (Criteria) this;
        }

        public Criteria andWatchuseridNotBetween(Integer value1, Integer value2) {
            addCriterion("watchuserId not between", value1, value2, "watchuserid");
            return (Criteria) this;
        }

        public Criteria andBewatchuseridIsNull() {
            addCriterion("bewatchuserId is null");
            return (Criteria) this;
        }

        public Criteria andBewatchuseridIsNotNull() {
            addCriterion("bewatchuserId is not null");
            return (Criteria) this;
        }

        public Criteria andBewatchuseridEqualTo(Integer value) {
            addCriterion("bewatchuserId =", value, "bewatchuserid");
            return (Criteria) this;
        }

        public Criteria andBewatchuseridNotEqualTo(Integer value) {
            addCriterion("bewatchuserId <>", value, "bewatchuserid");
            return (Criteria) this;
        }

        public Criteria andBewatchuseridGreaterThan(Integer value) {
            addCriterion("bewatchuserId >", value, "bewatchuserid");
            return (Criteria) this;
        }

        public Criteria andBewatchuseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("bewatchuserId >=", value, "bewatchuserid");
            return (Criteria) this;
        }

        public Criteria andBewatchuseridLessThan(Integer value) {
            addCriterion("bewatchuserId <", value, "bewatchuserid");
            return (Criteria) this;
        }

        public Criteria andBewatchuseridLessThanOrEqualTo(Integer value) {
            addCriterion("bewatchuserId <=", value, "bewatchuserid");
            return (Criteria) this;
        }

        public Criteria andBewatchuseridIn(List<Integer> values) {
            addCriterion("bewatchuserId in", values, "bewatchuserid");
            return (Criteria) this;
        }

        public Criteria andBewatchuseridNotIn(List<Integer> values) {
            addCriterion("bewatchuserId not in", values, "bewatchuserid");
            return (Criteria) this;
        }

        public Criteria andBewatchuseridBetween(Integer value1, Integer value2) {
            addCriterion("bewatchuserId between", value1, value2, "bewatchuserid");
            return (Criteria) this;
        }

        public Criteria andBewatchuseridNotBetween(Integer value1, Integer value2) {
            addCriterion("bewatchuserId not between", value1, value2, "bewatchuserid");
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
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
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