import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.pros.currency.model.ExchangeRate;
import com.pros.currency.service.CurrencyConverterService;

public class CurrencyConverterTest {

	private CurrencyConverterService currencyConverter;
	private ExchangeRate mockRate1;
	private ExchangeRate mockRate2;

	@Before
	public void setUp() {
		currencyConverter = new CurrencyConverterService();

		mockRate1 = mock(ExchangeRate.class);
		when(mockRate1.getFromCurrency()).thenReturn("USD");
		when(mockRate1.getToCurrency()).thenReturn("EUR");
		when(mockRate1.getDate()).thenReturn(new Date());
		when(mockRate1.getRate()).thenReturn(0.82);

		mockRate2 = mock(ExchangeRate.class);
		when(mockRate2.getFromCurrency()).thenReturn("EUR");
		when(mockRate2.getToCurrency()).thenReturn("GBP");
		when(mockRate2.getDate()).thenReturn(new Date());
		when(mockRate2.getRate()).thenReturn(0.87);
	}

	@Test
	public void testConvertCurrency() {
		currencyConverter.addExchangeRate(mockRate1.getFromCurrency(), mockRate1.getToCurrency(), mockRate1.getDate(),
				mockRate1.getRate());
		currencyConverter.addExchangeRate(mockRate2.getFromCurrency(), mockRate2.getToCurrency(), mockRate2.getDate(),
				mockRate2.getRate());

		double amountUSD = 100.0;
		String fromCurrency = "USD";
		String toCurrency = "GBP";
		Date date = new Date();
		double convertedAmount = currencyConverter.convertCurrency(amountUSD, fromCurrency, toCurrency, date);

		assertEquals(71.34, convertedAmount, 0.001);
	}

}
