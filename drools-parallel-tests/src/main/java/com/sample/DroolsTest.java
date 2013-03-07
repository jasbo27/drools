package com.sample;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.ObjectFilter;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.Activation;
import org.drools.runtime.rule.ActivationGroup;
import org.drools.runtime.rule.AgendaFilter;

import bank.model.Decyzja;
import bank.model.Klient;
import bank.model.Konto;
import bank.model.RynekKlienta;
import bank.model.StatusKonta;
import bank.model.Weryfikacja;
import bank.model.Windykacja;
import org.drools.runtime.rule.AgendaGroup;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
            KnowledgeBase kbase = readKnowledgeBase();
            StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
            KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
            
            Klient klient = prepareKlient();
            Konto konto = prepareKonto(klient);
            Windykacja windykacja = prepareWindykacja(klient);
            Weryfikacja weryfikacja = new Weryfikacja((new Date()).getTime(),klient);
            ksession.insert(klient);
            ksession.insert(konto);
            ksession.insert(windykacja);


            ksession.insert(weryfikacja);
            AgendaGroup group = ksession.getAgenda().getAgendaGroup("POBIERZ_DANE");
            group.setParallel(true);
            group.setMaxThreadCount(10);
            group.setFocus();
            ksession.fireAllRules();

            
            Collection<Object> wyniki = ksession.getObjects((new ObjectFilter() {
				public boolean accept(Object object) {
					return object instanceof Decyzja;
				}
			}));
            
            for(Object wynik:wyniki)
            {
            	Decyzja decyzja = (Decyzja)wynik;
            	System.out.println("Decyzja dla klienta " + decyzja.getKlient() + ": ");
            	System.out.println(decyzja);
            }
            
            logger.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static Konto prepareKonto(Klient klient) {
		return new Konto(StatusKonta.AKTYWNE,klient, 15000d);
	}

	private static Klient prepareKlient() {
		return new Klient(RynekKlienta.MASOWY, "Beata","Minkiewicz");
	}

	private static Windykacja prepareWindykacja(Klient klient) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse("2005-08-01");
		return new Windykacja(date.getTime(),klient);
	}

	private static KnowledgeBase readKnowledgeBase() throws Exception {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("rules/kredyty.drl"), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
    }

}
