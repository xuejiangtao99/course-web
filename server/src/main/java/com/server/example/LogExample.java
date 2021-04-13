package com.server.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andExcNameIsNull() {
            addCriterion("exc_name is null");
            return (Criteria) this;
        }

        public Criteria andExcNameIsNotNull() {
            addCriterion("exc_name is not null");
            return (Criteria) this;
        }

        public Criteria andExcNameEqualTo(String value) {
            addCriterion("exc_name =", value, "excName");
            return (Criteria) this;
        }

        public Criteria andExcNameNotEqualTo(String value) {
            addCriterion("exc_name <>", value, "excName");
            return (Criteria) this;
        }

        public Criteria andExcNameGreaterThan(String value) {
            addCriterion("exc_name >", value, "excName");
            return (Criteria) this;
        }

        public Criteria andExcNameGreaterThanOrEqualTo(String value) {
            addCriterion("exc_name >=", value, "excName");
            return (Criteria) this;
        }

        public Criteria andExcNameLessThan(String value) {
            addCriterion("exc_name <", value, "excName");
            return (Criteria) this;
        }

        public Criteria andExcNameLessThanOrEqualTo(String value) {
            addCriterion("exc_name <=", value, "excName");
            return (Criteria) this;
        }

        public Criteria andExcNameLike(String value) {
            addCriterion("exc_name like", value, "excName");
            return (Criteria) this;
        }

        public Criteria andExcNameNotLike(String value) {
            addCriterion("exc_name not like", value, "excName");
            return (Criteria) this;
        }

        public Criteria andExcNameIn(List<String> values) {
            addCriterion("exc_name in", values, "excName");
            return (Criteria) this;
        }

        public Criteria andExcNameNotIn(List<String> values) {
            addCriterion("exc_name not in", values, "excName");
            return (Criteria) this;
        }

        public Criteria andExcNameBetween(String value1, String value2) {
            addCriterion("exc_name between", value1, value2, "excName");
            return (Criteria) this;
        }

        public Criteria andExcNameNotBetween(String value1, String value2) {
            addCriterion("exc_name not between", value1, value2, "excName");
            return (Criteria) this;
        }

        public Criteria andExcMessageIsNull() {
            addCriterion("exc_message is null");
            return (Criteria) this;
        }

        public Criteria andExcMessageIsNotNull() {
            addCriterion("exc_message is not null");
            return (Criteria) this;
        }

        public Criteria andExcMessageEqualTo(String value) {
            addCriterion("exc_message =", value, "excMessage");
            return (Criteria) this;
        }

        public Criteria andExcMessageNotEqualTo(String value) {
            addCriterion("exc_message <>", value, "excMessage");
            return (Criteria) this;
        }

        public Criteria andExcMessageGreaterThan(String value) {
            addCriterion("exc_message >", value, "excMessage");
            return (Criteria) this;
        }

        public Criteria andExcMessageGreaterThanOrEqualTo(String value) {
            addCriterion("exc_message >=", value, "excMessage");
            return (Criteria) this;
        }

        public Criteria andExcMessageLessThan(String value) {
            addCriterion("exc_message <", value, "excMessage");
            return (Criteria) this;
        }

        public Criteria andExcMessageLessThanOrEqualTo(String value) {
            addCriterion("exc_message <=", value, "excMessage");
            return (Criteria) this;
        }

        public Criteria andExcMessageLike(String value) {
            addCriterion("exc_message like", value, "excMessage");
            return (Criteria) this;
        }

        public Criteria andExcMessageNotLike(String value) {
            addCriterion("exc_message not like", value, "excMessage");
            return (Criteria) this;
        }

        public Criteria andExcMessageIn(List<String> values) {
            addCriterion("exc_message in", values, "excMessage");
            return (Criteria) this;
        }

        public Criteria andExcMessageNotIn(List<String> values) {
            addCriterion("exc_message not in", values, "excMessage");
            return (Criteria) this;
        }

        public Criteria andExcMessageBetween(String value1, String value2) {
            addCriterion("exc_message between", value1, value2, "excMessage");
            return (Criteria) this;
        }

        public Criteria andExcMessageNotBetween(String value1, String value2) {
            addCriterion("exc_message not between", value1, value2, "excMessage");
            return (Criteria) this;
        }

        public Criteria andOperUserIdIsNull() {
            addCriterion("oper_user_id is null");
            return (Criteria) this;
        }

        public Criteria andOperUserIdIsNotNull() {
            addCriterion("oper_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperUserIdEqualTo(String value) {
            addCriterion("oper_user_id =", value, "operUserId");
            return (Criteria) this;
        }

        public Criteria andOperUserIdNotEqualTo(String value) {
            addCriterion("oper_user_id <>", value, "operUserId");
            return (Criteria) this;
        }

        public Criteria andOperUserIdGreaterThan(String value) {
            addCriterion("oper_user_id >", value, "operUserId");
            return (Criteria) this;
        }

        public Criteria andOperUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("oper_user_id >=", value, "operUserId");
            return (Criteria) this;
        }

        public Criteria andOperUserIdLessThan(String value) {
            addCriterion("oper_user_id <", value, "operUserId");
            return (Criteria) this;
        }

        public Criteria andOperUserIdLessThanOrEqualTo(String value) {
            addCriterion("oper_user_id <=", value, "operUserId");
            return (Criteria) this;
        }

        public Criteria andOperUserIdLike(String value) {
            addCriterion("oper_user_id like", value, "operUserId");
            return (Criteria) this;
        }

        public Criteria andOperUserIdNotLike(String value) {
            addCriterion("oper_user_id not like", value, "operUserId");
            return (Criteria) this;
        }

        public Criteria andOperUserIdIn(List<String> values) {
            addCriterion("oper_user_id in", values, "operUserId");
            return (Criteria) this;
        }

        public Criteria andOperUserIdNotIn(List<String> values) {
            addCriterion("oper_user_id not in", values, "operUserId");
            return (Criteria) this;
        }

        public Criteria andOperUserIdBetween(String value1, String value2) {
            addCriterion("oper_user_id between", value1, value2, "operUserId");
            return (Criteria) this;
        }

        public Criteria andOperUserIdNotBetween(String value1, String value2) {
            addCriterion("oper_user_id not between", value1, value2, "operUserId");
            return (Criteria) this;
        }

        public Criteria andOperUserNameIsNull() {
            addCriterion("oper_user_name is null");
            return (Criteria) this;
        }

        public Criteria andOperUserNameIsNotNull() {
            addCriterion("oper_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andOperUserNameEqualTo(String value) {
            addCriterion("oper_user_name =", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameNotEqualTo(String value) {
            addCriterion("oper_user_name <>", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameGreaterThan(String value) {
            addCriterion("oper_user_name >", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("oper_user_name >=", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameLessThan(String value) {
            addCriterion("oper_user_name <", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameLessThanOrEqualTo(String value) {
            addCriterion("oper_user_name <=", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameLike(String value) {
            addCriterion("oper_user_name like", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameNotLike(String value) {
            addCriterion("oper_user_name not like", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameIn(List<String> values) {
            addCriterion("oper_user_name in", values, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameNotIn(List<String> values) {
            addCriterion("oper_user_name not in", values, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameBetween(String value1, String value2) {
            addCriterion("oper_user_name between", value1, value2, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameNotBetween(String value1, String value2) {
            addCriterion("oper_user_name not between", value1, value2, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperMethodIsNull() {
            addCriterion("oper_method is null");
            return (Criteria) this;
        }

        public Criteria andOperMethodIsNotNull() {
            addCriterion("oper_method is not null");
            return (Criteria) this;
        }

        public Criteria andOperMethodEqualTo(String value) {
            addCriterion("oper_method =", value, "operMethod");
            return (Criteria) this;
        }

        public Criteria andOperMethodNotEqualTo(String value) {
            addCriterion("oper_method <>", value, "operMethod");
            return (Criteria) this;
        }

        public Criteria andOperMethodGreaterThan(String value) {
            addCriterion("oper_method >", value, "operMethod");
            return (Criteria) this;
        }

        public Criteria andOperMethodGreaterThanOrEqualTo(String value) {
            addCriterion("oper_method >=", value, "operMethod");
            return (Criteria) this;
        }

        public Criteria andOperMethodLessThan(String value) {
            addCriterion("oper_method <", value, "operMethod");
            return (Criteria) this;
        }

        public Criteria andOperMethodLessThanOrEqualTo(String value) {
            addCriterion("oper_method <=", value, "operMethod");
            return (Criteria) this;
        }

        public Criteria andOperMethodLike(String value) {
            addCriterion("oper_method like", value, "operMethod");
            return (Criteria) this;
        }

        public Criteria andOperMethodNotLike(String value) {
            addCriterion("oper_method not like", value, "operMethod");
            return (Criteria) this;
        }

        public Criteria andOperMethodIn(List<String> values) {
            addCriterion("oper_method in", values, "operMethod");
            return (Criteria) this;
        }

        public Criteria andOperMethodNotIn(List<String> values) {
            addCriterion("oper_method not in", values, "operMethod");
            return (Criteria) this;
        }

        public Criteria andOperMethodBetween(String value1, String value2) {
            addCriterion("oper_method between", value1, value2, "operMethod");
            return (Criteria) this;
        }

        public Criteria andOperMethodNotBetween(String value1, String value2) {
            addCriterion("oper_method not between", value1, value2, "operMethod");
            return (Criteria) this;
        }

        public Criteria andOperUriIsNull() {
            addCriterion("oper_uri is null");
            return (Criteria) this;
        }

        public Criteria andOperUriIsNotNull() {
            addCriterion("oper_uri is not null");
            return (Criteria) this;
        }

        public Criteria andOperUriEqualTo(String value) {
            addCriterion("oper_uri =", value, "operUri");
            return (Criteria) this;
        }

        public Criteria andOperUriNotEqualTo(String value) {
            addCriterion("oper_uri <>", value, "operUri");
            return (Criteria) this;
        }

        public Criteria andOperUriGreaterThan(String value) {
            addCriterion("oper_uri >", value, "operUri");
            return (Criteria) this;
        }

        public Criteria andOperUriGreaterThanOrEqualTo(String value) {
            addCriterion("oper_uri >=", value, "operUri");
            return (Criteria) this;
        }

        public Criteria andOperUriLessThan(String value) {
            addCriterion("oper_uri <", value, "operUri");
            return (Criteria) this;
        }

        public Criteria andOperUriLessThanOrEqualTo(String value) {
            addCriterion("oper_uri <=", value, "operUri");
            return (Criteria) this;
        }

        public Criteria andOperUriLike(String value) {
            addCriterion("oper_uri like", value, "operUri");
            return (Criteria) this;
        }

        public Criteria andOperUriNotLike(String value) {
            addCriterion("oper_uri not like", value, "operUri");
            return (Criteria) this;
        }

        public Criteria andOperUriIn(List<String> values) {
            addCriterion("oper_uri in", values, "operUri");
            return (Criteria) this;
        }

        public Criteria andOperUriNotIn(List<String> values) {
            addCriterion("oper_uri not in", values, "operUri");
            return (Criteria) this;
        }

        public Criteria andOperUriBetween(String value1, String value2) {
            addCriterion("oper_uri between", value1, value2, "operUri");
            return (Criteria) this;
        }

        public Criteria andOperUriNotBetween(String value1, String value2) {
            addCriterion("oper_uri not between", value1, value2, "operUri");
            return (Criteria) this;
        }

        public Criteria andOperIpIsNull() {
            addCriterion("oper_ip is null");
            return (Criteria) this;
        }

        public Criteria andOperIpIsNotNull() {
            addCriterion("oper_ip is not null");
            return (Criteria) this;
        }

        public Criteria andOperIpEqualTo(String value) {
            addCriterion("oper_ip =", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpNotEqualTo(String value) {
            addCriterion("oper_ip <>", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpGreaterThan(String value) {
            addCriterion("oper_ip >", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpGreaterThanOrEqualTo(String value) {
            addCriterion("oper_ip >=", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpLessThan(String value) {
            addCriterion("oper_ip <", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpLessThanOrEqualTo(String value) {
            addCriterion("oper_ip <=", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpLike(String value) {
            addCriterion("oper_ip like", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpNotLike(String value) {
            addCriterion("oper_ip not like", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpIn(List<String> values) {
            addCriterion("oper_ip in", values, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpNotIn(List<String> values) {
            addCriterion("oper_ip not in", values, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpBetween(String value1, String value2) {
            addCriterion("oper_ip between", value1, value2, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpNotBetween(String value1, String value2) {
            addCriterion("oper_ip not between", value1, value2, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperVerIsNull() {
            addCriterion("oper_ver is null");
            return (Criteria) this;
        }

        public Criteria andOperVerIsNotNull() {
            addCriterion("oper_ver is not null");
            return (Criteria) this;
        }

        public Criteria andOperVerEqualTo(String value) {
            addCriterion("oper_ver =", value, "operVer");
            return (Criteria) this;
        }

        public Criteria andOperVerNotEqualTo(String value) {
            addCriterion("oper_ver <>", value, "operVer");
            return (Criteria) this;
        }

        public Criteria andOperVerGreaterThan(String value) {
            addCriterion("oper_ver >", value, "operVer");
            return (Criteria) this;
        }

        public Criteria andOperVerGreaterThanOrEqualTo(String value) {
            addCriterion("oper_ver >=", value, "operVer");
            return (Criteria) this;
        }

        public Criteria andOperVerLessThan(String value) {
            addCriterion("oper_ver <", value, "operVer");
            return (Criteria) this;
        }

        public Criteria andOperVerLessThanOrEqualTo(String value) {
            addCriterion("oper_ver <=", value, "operVer");
            return (Criteria) this;
        }

        public Criteria andOperVerLike(String value) {
            addCriterion("oper_ver like", value, "operVer");
            return (Criteria) this;
        }

        public Criteria andOperVerNotLike(String value) {
            addCriterion("oper_ver not like", value, "operVer");
            return (Criteria) this;
        }

        public Criteria andOperVerIn(List<String> values) {
            addCriterion("oper_ver in", values, "operVer");
            return (Criteria) this;
        }

        public Criteria andOperVerNotIn(List<String> values) {
            addCriterion("oper_ver not in", values, "operVer");
            return (Criteria) this;
        }

        public Criteria andOperVerBetween(String value1, String value2) {
            addCriterion("oper_ver between", value1, value2, "operVer");
            return (Criteria) this;
        }

        public Criteria andOperVerNotBetween(String value1, String value2) {
            addCriterion("oper_ver not between", value1, value2, "operVer");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andOperModulIsNull() {
            addCriterion("oper_modul is null");
            return (Criteria) this;
        }

        public Criteria andOperModulIsNotNull() {
            addCriterion("oper_modul is not null");
            return (Criteria) this;
        }

        public Criteria andOperModulEqualTo(String value) {
            addCriterion("oper_modul =", value, "operModul");
            return (Criteria) this;
        }

        public Criteria andOperModulNotEqualTo(String value) {
            addCriterion("oper_modul <>", value, "operModul");
            return (Criteria) this;
        }

        public Criteria andOperModulGreaterThan(String value) {
            addCriterion("oper_modul >", value, "operModul");
            return (Criteria) this;
        }

        public Criteria andOperModulGreaterThanOrEqualTo(String value) {
            addCriterion("oper_modul >=", value, "operModul");
            return (Criteria) this;
        }

        public Criteria andOperModulLessThan(String value) {
            addCriterion("oper_modul <", value, "operModul");
            return (Criteria) this;
        }

        public Criteria andOperModulLessThanOrEqualTo(String value) {
            addCriterion("oper_modul <=", value, "operModul");
            return (Criteria) this;
        }

        public Criteria andOperModulLike(String value) {
            addCriterion("oper_modul like", value, "operModul");
            return (Criteria) this;
        }

        public Criteria andOperModulNotLike(String value) {
            addCriterion("oper_modul not like", value, "operModul");
            return (Criteria) this;
        }

        public Criteria andOperModulIn(List<String> values) {
            addCriterion("oper_modul in", values, "operModul");
            return (Criteria) this;
        }

        public Criteria andOperModulNotIn(List<String> values) {
            addCriterion("oper_modul not in", values, "operModul");
            return (Criteria) this;
        }

        public Criteria andOperModulBetween(String value1, String value2) {
            addCriterion("oper_modul between", value1, value2, "operModul");
            return (Criteria) this;
        }

        public Criteria andOperModulNotBetween(String value1, String value2) {
            addCriterion("oper_modul not between", value1, value2, "operModul");
            return (Criteria) this;
        }

        public Criteria andOperTypeIsNull() {
            addCriterion("oper_type is null");
            return (Criteria) this;
        }

        public Criteria andOperTypeIsNotNull() {
            addCriterion("oper_type is not null");
            return (Criteria) this;
        }

        public Criteria andOperTypeEqualTo(String value) {
            addCriterion("oper_type =", value, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeNotEqualTo(String value) {
            addCriterion("oper_type <>", value, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeGreaterThan(String value) {
            addCriterion("oper_type >", value, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeGreaterThanOrEqualTo(String value) {
            addCriterion("oper_type >=", value, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeLessThan(String value) {
            addCriterion("oper_type <", value, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeLessThanOrEqualTo(String value) {
            addCriterion("oper_type <=", value, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeLike(String value) {
            addCriterion("oper_type like", value, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeNotLike(String value) {
            addCriterion("oper_type not like", value, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeIn(List<String> values) {
            addCriterion("oper_type in", values, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeNotIn(List<String> values) {
            addCriterion("oper_type not in", values, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeBetween(String value1, String value2) {
            addCriterion("oper_type between", value1, value2, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeNotBetween(String value1, String value2) {
            addCriterion("oper_type not between", value1, value2, "operType");
            return (Criteria) this;
        }

        public Criteria andOperDescIsNull() {
            addCriterion("oper_desc is null");
            return (Criteria) this;
        }

        public Criteria andOperDescIsNotNull() {
            addCriterion("oper_desc is not null");
            return (Criteria) this;
        }

        public Criteria andOperDescEqualTo(String value) {
            addCriterion("oper_desc =", value, "operDesc");
            return (Criteria) this;
        }

        public Criteria andOperDescNotEqualTo(String value) {
            addCriterion("oper_desc <>", value, "operDesc");
            return (Criteria) this;
        }

        public Criteria andOperDescGreaterThan(String value) {
            addCriterion("oper_desc >", value, "operDesc");
            return (Criteria) this;
        }

        public Criteria andOperDescGreaterThanOrEqualTo(String value) {
            addCriterion("oper_desc >=", value, "operDesc");
            return (Criteria) this;
        }

        public Criteria andOperDescLessThan(String value) {
            addCriterion("oper_desc <", value, "operDesc");
            return (Criteria) this;
        }

        public Criteria andOperDescLessThanOrEqualTo(String value) {
            addCriterion("oper_desc <=", value, "operDesc");
            return (Criteria) this;
        }

        public Criteria andOperDescLike(String value) {
            addCriterion("oper_desc like", value, "operDesc");
            return (Criteria) this;
        }

        public Criteria andOperDescNotLike(String value) {
            addCriterion("oper_desc not like", value, "operDesc");
            return (Criteria) this;
        }

        public Criteria andOperDescIn(List<String> values) {
            addCriterion("oper_desc in", values, "operDesc");
            return (Criteria) this;
        }

        public Criteria andOperDescNotIn(List<String> values) {
            addCriterion("oper_desc not in", values, "operDesc");
            return (Criteria) this;
        }

        public Criteria andOperDescBetween(String value1, String value2) {
            addCriterion("oper_desc between", value1, value2, "operDesc");
            return (Criteria) this;
        }

        public Criteria andOperDescNotBetween(String value1, String value2) {
            addCriterion("oper_desc not between", value1, value2, "operDesc");
            return (Criteria) this;
        }

        public Criteria andRequiredMethodIsNull() {
            addCriterion("required_method is null");
            return (Criteria) this;
        }

        public Criteria andRequiredMethodIsNotNull() {
            addCriterion("required_method is not null");
            return (Criteria) this;
        }

        public Criteria andRequiredMethodEqualTo(String value) {
            addCriterion("required_method =", value, "requiredMethod");
            return (Criteria) this;
        }

        public Criteria andRequiredMethodNotEqualTo(String value) {
            addCriterion("required_method <>", value, "requiredMethod");
            return (Criteria) this;
        }

        public Criteria andRequiredMethodGreaterThan(String value) {
            addCriterion("required_method >", value, "requiredMethod");
            return (Criteria) this;
        }

        public Criteria andRequiredMethodGreaterThanOrEqualTo(String value) {
            addCriterion("required_method >=", value, "requiredMethod");
            return (Criteria) this;
        }

        public Criteria andRequiredMethodLessThan(String value) {
            addCriterion("required_method <", value, "requiredMethod");
            return (Criteria) this;
        }

        public Criteria andRequiredMethodLessThanOrEqualTo(String value) {
            addCriterion("required_method <=", value, "requiredMethod");
            return (Criteria) this;
        }

        public Criteria andRequiredMethodLike(String value) {
            addCriterion("required_method like", value, "requiredMethod");
            return (Criteria) this;
        }

        public Criteria andRequiredMethodNotLike(String value) {
            addCriterion("required_method not like", value, "requiredMethod");
            return (Criteria) this;
        }

        public Criteria andRequiredMethodIn(List<String> values) {
            addCriterion("required_method in", values, "requiredMethod");
            return (Criteria) this;
        }

        public Criteria andRequiredMethodNotIn(List<String> values) {
            addCriterion("required_method not in", values, "requiredMethod");
            return (Criteria) this;
        }

        public Criteria andRequiredMethodBetween(String value1, String value2) {
            addCriterion("required_method between", value1, value2, "requiredMethod");
            return (Criteria) this;
        }

        public Criteria andRequiredMethodNotBetween(String value1, String value2) {
            addCriterion("required_method not between", value1, value2, "requiredMethod");
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