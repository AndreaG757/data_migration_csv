package utility;

import com.opencsv.bean.CsvToBeanBuilder;
import model.Assicurato;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class ParseCsv {

    public static List<Assicurato> parseAssicurato() throws FileNotFoundException {

        List<Assicurato> assicurati = new CsvToBeanBuilder(new FileReader("C:/Users/vecch/CSV/oldassicurato.csv"))
                .withType(Assicurato.class)
                .build()
                .parse();

        return assicurati;

    }


}
