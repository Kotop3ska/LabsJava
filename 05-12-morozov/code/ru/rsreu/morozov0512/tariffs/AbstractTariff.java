package ru.rsreu.morozov0512.tariffs;

import ru.rsreu.morozov0512.datatypes.TariffName;
import ru.rsreu.morozov0512.servicepackageutils.ServicePackageUpdater;
import ru.rsreu.morozov0512.datatypes.ServicePackage;

import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.morozov0512.datatypes.AdditionalServiceType;
import ru.rsreu.morozov0512.tablebuilder.TableRepresentable;
import ru.rsreu.morozov0512.tablebuilder.TableBuilder;

public abstract class AbstractTariff implements Comparable<AbstractTariff>, TableRepresentable {

	private TariffName name;
	private double basePrice;
	private ServicePackage servicePackage;
	private int clientsCount;

	public AbstractTariff(TariffName name, double basePrice, ServicePackage servicePackage, int clientsCount) {
		this.name = name;
		this.basePrice = basePrice;
		this.servicePackage = servicePackage;
		this.clientsCount = clientsCount;
	}

	public TariffName getName() {
		return this.name;
	}

	public int getClientsCount() {
		return this.clientsCount;
	}

	public double getMonthlyCost() {
		return this.calculateMonthlyCost();
	}

	public double getTotalRevenue() {
		return this.calculateMonthlyCost() * this.clientsCount;
	}

	public ServicePackage getServicePackage() {
		return this.servicePackage;
	}

	@Override
	public int compareTo(AbstractTariff otherTariff) {
		return Double.compare(this.calculateMonthlyCost(), otherTariff.calculateMonthlyCost());
	}

	@Override
	public String toString() {
		TableBuilder tableBuilder = new TableBuilder();

		String[] tableHeaders = this.getTableHeaders();
		Object[][] dataForTable = new Object[][] { this.getRowData() };

		return tableBuilder.buildTable(tableHeaders, dataForTable);
	}

	@Override
	public String[] getTableHeaders() {
		Resourcer resourcer = ProjectResourcer.getInstance();
		return new String[] { resourcer.getString("message.headers.name"),
				resourcer.getString("message.headers.gigabytes"), resourcer.getString("message.headers.minutes"),
				resourcer.getString("message.headers.sms"), resourcer.getString("message.headers.cost"),
				resourcer.getString("message.headers.clients"), resourcer.getString("message.headers.activation.fee") };
	}

	@Override
	public Object[] getRowData() {
		return new Object[] { this.name.toString(), this.servicePackage.getGigabytesCount(),
				this.servicePackage.getMinutesCount(), this.servicePackage.getSmsCount(), this.calculateMonthlyCost(),
				this.clientsCount, this.calculateActivationFee() };
	}

	protected abstract double calculateMonthlyCost();

	protected abstract double calculateActivationFee();

	protected void updateServicePackage(AdditionalServiceType... additionalServiceTypes) {
		ServicePackageUpdater servicePackageUpdater = new ServicePackageUpdater(this.servicePackage);
		servicePackageUpdater.updateServicePackage(additionalServiceTypes);
	}

	protected double getBasePrice() {
		return this.basePrice;
	}

}
