package com.hu.ssm.base.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Generator implements Serializable {
    private Long id;

    private BigDecimal money1;

    private Byte money2;

    private Integer money3;

    private String name;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMoney1() {
        return money1;
    }

    public void setMoney1(BigDecimal money1) {
        this.money1 = money1;
    }

    public Byte getMoney2() {
        return money2;
    }

    public void setMoney2(Byte money2) {
        this.money2 = money2;
    }

    public Integer getMoney3() {
        return money3;
    }

    public void setMoney3(Integer money3) {
        this.money3 = money3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Generator other = (Generator) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMoney1() == null ? other.getMoney1() == null : this.getMoney1().equals(other.getMoney1()))
            && (this.getMoney2() == null ? other.getMoney2() == null : this.getMoney2().equals(other.getMoney2()))
            && (this.getMoney3() == null ? other.getMoney3() == null : this.getMoney3().equals(other.getMoney3()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMoney1() == null) ? 0 : getMoney1().hashCode());
        result = prime * result + ((getMoney2() == null) ? 0 : getMoney2().hashCode());
        result = prime * result + ((getMoney3() == null) ? 0 : getMoney3().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }
}