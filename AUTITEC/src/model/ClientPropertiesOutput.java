package model;

import java.util.Date;
import java.util.List;

public class ClientPropertiesOutput {

	private int id;
	private String motive;
	private String exitFiscalNote;
	private Date exitDate;
	private List<ClientPropertiesMaterial> cpmList;
	private ClientProperties cp;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMotive() {
		return motive;
	}
	public void setMotive(String motive) {
		this.motive = motive;
	}
	public String getExitFiscalNote() {
		return exitFiscalNote;
	}
	public void setExitFiscalNote(String exitFiscalNote) {
		this.exitFiscalNote = exitFiscalNote;
	}
	public Date getExitDate() {
		return exitDate;
	}
	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}
	public List<ClientPropertiesMaterial> getCpList() {
	    return cpmList;
    }
	public void setCpmList(List<ClientPropertiesMaterial> cpList) {
	    this.cpmList = cpList;
    }
	public ClientProperties getCp() {
	    return cp;
    }
	public void setCp(ClientProperties cp) {
	    this.cp = cp;
    }
}
