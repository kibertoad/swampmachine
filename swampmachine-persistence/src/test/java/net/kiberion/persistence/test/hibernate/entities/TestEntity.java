package net.kiberion.persistence.test.hibernate.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import net.kiberion.persistence.common.BaseEntity;

@Entity
@Table(name = "test_entity")
public class TestEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8644035838781105734L;

}
