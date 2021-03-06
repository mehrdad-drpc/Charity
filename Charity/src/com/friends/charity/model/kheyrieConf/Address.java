package com.friends.charity.model.kheyrieConf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.friends.charity.model.BaseEntity;

@Table(name = "ADDRESS")
@Entity
@NamedQueries({ @NamedQuery(name = "address", query = "select add from Address add where add.telAdds.id=:telAddId")

})
public class Address extends BaseEntity {
	private String kheyriehAdr;
	@ManyToOne
	private TelAdd telAdds;

	public String getKheyriehAdr() {
		return kheyriehAdr;
	}

	public void setKheyriehAdr(String kheyriehAdr) {
		this.kheyriehAdr = kheyriehAdr;
	}

	public TelAdd getTelAdd() {
		if (telAdds == null) {
			telAdds = new TelAdd();
		}
		return telAdds;
	}

	public void setTelAdd(TelAdd telAdd) {
		this.telAdds = telAdd;
	}
}
