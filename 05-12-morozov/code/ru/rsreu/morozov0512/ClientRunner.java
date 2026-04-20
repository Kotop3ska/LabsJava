package ru.rsreu.morozov0512;

import ru.rsreu.morozov0512.tariffs.AbstractTariff;
import ru.rsreu.morozov0512.tariffutils.TariffsInitializer;
import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.morozov0512.datatypes.TariffName;
import ru.rsreu.morozov0512.mobilecompany.MobileCompany;

public class ClientRunner {

	private ClientRunner() {
	}

	public static void main(String[] args) {

		Resourcer resourcer = ProjectResourcer.getInstance();
		StringBuilder resultString = new StringBuilder();

		TariffsInitializer tariffsInitializer = new TariffsInitializer();
		AbstractTariff[] mobileTariffs = tariffsInitializer.initializeTariffs();
		MobileCompany mobileCompany = new MobileCompany(mobileTariffs);

		int clientsCount = mobileCompany.getClientsCount();
		double totalRevenue = mobileCompany.getTotalRevenue();

		resultString.append(String.format(resourcer.getString("message.out.clients.count"), clientsCount))
				.append(String.format(resourcer.getString("message.out.revenue"), totalRevenue))
				.append(resourcer.getString("message.out.tariffs.source")).append(mobileCompany.toString());

		mobileCompany.sortTariffsByMonthlyCost();

		resultString.append(resourcer.getString("message.out.tariffs.sorted")).append(mobileCompany.toString());

		AbstractTariff tariff = mobileCompany.findTariffByName(TariffName.INTHERNET_TARIFF);

		resultString.append(resourcer.getString("message.out.tariffs.found")).append(tariff.toString());

		System.out.println(resultString.toString());
	}

}
