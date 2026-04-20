package ru.rsreu.morozov0512.mobilecompany;

import ru.rsreu.morozov0512.tariffs.AbstractTariff;
import ru.rsreu.morozov0512.datatypes.TariffName;
import ru.rsreu.morozov0512.tariffutils.VerifiedTariffCreator;
import ru.rsreu.morozov0512.tablebuilder.TableBuilder;
import java.util.Arrays;
import java.util.stream.Stream;

public class MobileCompany {

	private AbstractTariff[] tariffs;

	public MobileCompany(AbstractTariff[] tariffs) {
		this.tariffs = tariffs;
	}

	public AbstractTariff findTariffByName(TariffName tariffName) {
		AbstractTariff tariff = Stream.of(this.tariffs).filter(t -> t.getName() == tariffName).findFirst()
				.orElse(VerifiedTariffCreator.DEFAULT);

		return tariff;
	}

	public void sortTariffsByMonthlyCost() {
		Arrays.sort(this.tariffs);
	}

	public int getClientsCount() {
		return Stream.of(this.tariffs).mapToInt(AbstractTariff::getClientsCount).sum();
	}

	public double getTotalRevenue() {
		return Stream.of(this.tariffs).mapToDouble(AbstractTariff::getTotalRevenue).sum();
	}

	@Override
	public String toString() {

		String[] tariffHeaders = this.getTariffHeaders();
		Object[][] tariffsData = this.getTariffsData();

		TableBuilder tableBuilder = new TableBuilder();

		return tableBuilder.buildTable(tariffHeaders, tariffsData);
	}

	private String[] getTariffHeaders() {
		return this.tariffs[0].getTableHeaders();
	}

	private Object[][] getTariffsData() {
		return Stream.of(this.tariffs).map(AbstractTariff::getRowData).toArray(Object[][]::new);
	}
}
