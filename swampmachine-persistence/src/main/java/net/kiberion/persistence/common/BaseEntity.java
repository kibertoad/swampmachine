package net.kiberion.persistence.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.NoArgGenerator;

@MappedSuperclass
public abstract class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6119877887146169484L;

	private static final NoArgGenerator generator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
	
	private String oid;

	public BaseEntity() {
		oid = generator.generate().toString();
	}
	
    @Id
    @Column(name = "oid", unique = true, nullable = false)
    public String getOid() {
        return oid;
    }
    
    protected void setOid(String oid) {
        this.oid = oid;
    }
	
}
