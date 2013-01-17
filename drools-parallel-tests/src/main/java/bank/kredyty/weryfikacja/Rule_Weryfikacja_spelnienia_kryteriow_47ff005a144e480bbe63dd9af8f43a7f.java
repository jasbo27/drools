
package bank.kredyty.weryfikacja;

import bank.model.Konto;
import bank.utils.DateUtils;
import bank.model.DecyzjaNegatywna;
import bank.model.Windykacja;
import bank.kredyty.weryfikacja.*;
import java.util.Date;
import bank.model.Klient;
import bank.model.Weryfikacja;
import bank.model.KredytOdnawialny;
import bank.model.DecyzjaPozytywna;
import bank.model.RynekKlienta;
import bank.model.Decyzja;
import bank.model.StatusKonta;

public class Rule_Weryfikacja_spelnienia_kryteriow_47ff005a144e480bbe63dd9af8f43a7f {
	private static final long serialVersionUID = 510l;

	public static void defaultConsequence(
			org.drools.spi.KnowledgeHelper drools,
			bank.model.Weryfikacja $weryfikacja,
			org.drools.FactHandle $weryfikacja__Handle__) throws Exception {
		org.drools.runtime.rule.RuleContext kcontext = drools;
		Decyzja decyzja = new DecyzjaNegatywna($weryfikacja,
				"Brak spełnienia wystarczających kryteriów kredytu");
		drools.insert(decyzja);
	}

}