package com.hu.ssm.base.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GeneratorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GeneratorExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMoney1IsNull() {
            addCriterion("money1 is null");
            return (Criteria) this;
        }

        public Criteria andMoney1IsNotNull() {
            addCriterion("money1 is not null");
            return (Criteria) this;
        }

        public Criteria andMoney1EqualTo(BigDecimal value) {
            addCriterion("money1 =", value, "money1");
            return (Criteria) this;
        }

        public Criteria andMoney1NotEqualTo(BigDecimal value) {
            addCriterion("money1 <>", value, "money1");
            return (Criteria) this;
        }

        public Criteria andMoney1GreaterThan(BigDecimal value) {
            addCriterion("money1 >", value, "money1");
            return (Criteria) this;
        }

        public Criteria andMoney1GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("money1 >=", value, "money1");
            return (Criteria) this;
        }

        public Criteria andMoney1LessThan(BigDecimal value) {
            addCriterion("money1 <", value, "money1");
            return (Criteria) this;
        }

        public Criteria andMoney1LessThanOrEqualTo(BigDecimal value) {
            addCriterion("money1 <=", value, "money1");
            return (Criteria) this;
        }

        public Criteria andMoney1In(List<BigDecimal> values) {
            addCriterion("money1 in", values, "money1");
            return (Criteria) this;
        }

        public Criteria andMoney1NotIn(List<BigDecimal> values) {
            addCriterion("money1 not in", values, "money1");
            return (Criteria) this;
        }

        public Criteria andMoney1Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("money1 between", value1, value2, "money1");
            return (Criteria) this;
        }

        public Criteria andMoney1NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money1 not between", value1, value2, "money1");
            return (Criteria) this;
        }

        public Criteria andMoney2IsNull() {
            addCriterion("money2 is null");
            return (Criteria) this;
        }

        public Criteria andMoney2IsNotNull() {
            addCriterion("money2 is not null");
            return (Criteria) this;
        }

        public Criteria andMoney2EqualTo(Byte value) {
            addCriterion("money2 =", value, "money2");
            return (Criteria) this;
        }

        public Criteria andMoney2NotEqualTo(Byte value) {
            addCriterion("money2 <>", value, "money2");
            return (Criteria) this;
        }

        public Criteria andMoney2GreaterThan(Byte value) {
            addCriterion("money2 >", value, "money2");
            return (Criteria) this;
        }

        public Criteria andMoney2GreaterThanOrEqualTo(Byte value) {
            addCriterion("money2 >=", value, "money2");
            return (Criteria) this;
        }

        public Criteria andMoney2LessThan(Byte value) {
            addCriterion("money2 <", value, "money2");
            return (Criteria) this;
        }

        public Criteria andMoney2LessThanOrEqualTo(Byte value) {
            addCriterion("money2 <=", value, "money2");
            return (Criteria) this;
        }

        public Criteria andMoney2In(List<Byte> values) {
            addCriterion("money2 in", values, "money2");
            return (Criteria) this;
        }

        public Criteria andMoney2NotIn(List<Byte> values) {
            addCriterion("money2 not in", values, "money2");
            return (Criteria) this;
        }

        public Criteria andMoney2Between(Byte value1, Byte value2) {
            addCriterion("money2 between", value1, value2, "money2");
            return (Criteria) this;
        }

        public Criteria andMoney2NotBetween(Byte value1, Byte value2) {
            addCriterion("money2 not between", value1, value2, "money2");
            return (Criteria) this;
        }

        public Criteria andMoney3IsNull() {
            addCriterion("money3 is null");
            return (Criteria) this;
        }

        public Criteria andMoney3IsNotNull() {
            addCriterion("money3 is not null");
            return (Criteria) this;
        }

        public Criteria andMoney3EqualTo(Integer value) {
            addCriterion("money3 =", value, "money3");
            return (Criteria) this;
        }

        public Criteria andMoney3NotEqualTo(Integer value) {
            addCriterion("money3 <>", value, "money3");
            return (Criteria) this;
        }

        public Criteria andMoney3GreaterThan(Integer value) {
            addCriterion("money3 >", value, "money3");
            return (Criteria) this;
        }

        public Criteria andMoney3GreaterThanOrEqualTo(Integer value) {
            addCriterion("money3 >=", value, "money3");
            return (Criteria) this;
        }

        public Criteria andMoney3LessThan(Integer value) {
            addCriterion("money3 <", value, "money3");
            return (Criteria) this;
        }

        public Criteria andMoney3LessThanOrEqualTo(Integer value) {
            addCriterion("money3 <=", value, "money3");
            return (Criteria) this;
        }

        public Criteria andMoney3In(List<Integer> values) {
            addCriterion("money3 in", values, "money3");
            return (Criteria) this;
        }

        public Criteria andMoney3NotIn(List<Integer> values) {
            addCriterion("money3 not in", values, "money3");
            return (Criteria) this;
        }

        public Criteria andMoney3Between(Integer value1, Integer value2) {
            addCriterion("money3 between", value1, value2, "money3");
            return (Criteria) this;
        }

        public Criteria andMoney3NotBetween(Integer value1, Integer value2) {
            addCriterion("money3 not between", value1, value2, "money3");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
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