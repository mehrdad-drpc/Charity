package com.friends.charity.view.template.general.menu.file.register.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.friends.charity.business.logic.CalendarFormat;
import com.friends.charity.business.service.GeneralService;
import com.friends.charity.business.service.model.LoginService;
import com.friends.charity.model.Login;
import com.friends.charity.model.MaskanType;
import com.friends.charity.model.MoshakhasateMotaghazi;
import com.friends.charity.model.farzand.Farzandan;

@Named
public class MadadJoEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	private MoshakhasateMotaghazi motaghazi;
	private List<Farzandan> farzandans;
	private DataModel<Farzandan> dataModel;
	private Farzandan farzandan;
	private Login login;
	private GeneralService generalService;
	private LoginService loginService;
	private String username;

	public MoshakhasateMotaghazi getMotaghazi() {
		if (motaghazi == null) {
			motaghazi = new MoshakhasateMotaghazi();
		}
		return motaghazi;
	}

	public void setMotaghazi(MoshakhasateMotaghazi motaghazi) {
		this.motaghazi = motaghazi;
	}

	public List<Farzandan> getFarzandans() {
		if (farzandans == null) {
			farzandans = new ArrayList<>();
		}
		return farzandans;
	}

	public void setFarzandans(List<Farzandan> farzandans) {
		this.farzandans = farzandans;
	}

	public DataModel<Farzandan> getDataModel() {
		if (dataModel == null) {
			dataModel = new ListDataModel<>();
			dataModel.setWrappedData(getFarzandans());
		}
		return dataModel;
	}

	public void setDataModel(DataModel<Farzandan> dataModel) {
		this.dataModel = dataModel;
	}

	public Farzandan getFarzandan() {
		if (farzandan == null) {
			farzandan = new Farzandan();
		}
		return farzandan;
	}

	public void setFarzandan(Farzandan farzandan) {
		this.farzandan = farzandan;
	}

	public Login getLogin() {
		if (login == null) {
			login = new Login();
		}
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public GeneralService getGeneralService() {
		if (generalService == null) {
			generalService = new GeneralService();
		}
		return generalService;
	}

	public void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}

	public LoginService getLoginService() {
		if (loginService == null) {
			loginService = new LoginService();
		}
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
//		Login login = null;
//		login = getLoginService().getCorrectUsername(username);
//		if (login == null) {
//			FacesContext.getCurrentInstance().addMessage(
//					null,
//					new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطا",
//							"...نام کاربری موجود میباشد"));
//			setUsername(null);
//		} else {
//			getLogin().getUsernamePassword().setUsername(getUsername());
//		}
	}

	public MaskanType[] getMaskanValues() {
		return MaskanType.values();
	}

	public SelectItem[] getMaskanValue() {
		SelectItem items[] = new SelectItem[MaskanType.values().length];
		int i = 0;
		for (MaskanType g : MaskanType.values()) {
			items[i++] = new SelectItem(g, g.getType());
		}
		return items;
	}

	/**
	 * .برای دکمه ثبت
	 */
	public void btnSubmit(ActionEvent event) {
		getFarzandans().add(getFarzandan());
		// PersianDate(getFarzandan().getDate());
		setFarzandan(null);
	}

	public String PersianDate(Date date) {
		String str = date.toString();
		return str;
	}

	public void saved(ActionEvent actionEvent) {
		FacesMessage message = new FacesMessage();
		FacesContext context = FacesContext.getCurrentInstance();
		getMotaghazi().setBirthday(
				CalendarFormat.getGerigorian(getMotaghazi().getMySelfDate()));
		if (getMotaghazi().getMyWifeDate() != null) {
			getMotaghazi().setHamsarBirthday(
					CalendarFormat
							.getGerigorian(getMotaghazi().getMyWifeDate()));
		}
		for (Farzandan farzandan : getFarzandans()) {
			if (farzandan.getDate() != null) {
				farzandan.setBirthday(CalendarFormat.getGerigorian(farzandan
						.getDate()));
			}
		}
		getMotaghazi().setFarzandans(getFarzandans());
		getLogin().setUser(getMotaghazi());
		try {
			getGeneralService().save(getLogin());
			nullEntity();
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			message.setDetail("ذخیره");
			message.setSummary(".اطلاعات شما با موفقیت ذخیره شد");
			context.addMessage(null, message);
		} catch (Exception e) {
			message.setSeverity(FacesMessage.SEVERITY_FATAL);
			message.setDetail("خطا");
			message.setSummary(".مجددا امتحان فرمائید");
			nullEntity();
			context.addMessage(null, message);
			e.printStackTrace();
		}
	}

	public void nullEntity() {
		setFarzandans(null);
		setDataModel(null);
		setMotaghazi(null);
		setLogin(null);
	}
	/**
	 * for birthday commite
	 * 
	 * public void btn(ActionEvent actionEvent) { GeneralDao dao = new
	 * GeneralDao();
	 * getMotaghazi().setBirthday(CalendarFormat.getGerigorian(getDate()));
	 * System.out.println(getMotaghazi().getBirthday().getTime()); try {
	 * dao.save(getMotaghazi()); } catch (Exception e) { e.printStackTrace(); }
	 */
}
