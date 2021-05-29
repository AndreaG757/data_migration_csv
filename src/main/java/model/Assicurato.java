package model;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

import java.util.Date;

@Data @Builder @AllArgsConstructor @NoArgsConstructor @ToString
public class Assicurato {

    @CsvBindByName(column = "id")
    private Long id;

    @CsvBindByName(column = "nome")
    private String nome;

    @CsvBindByName(column = "cognome")
    private String cognome;

    @CsvBindByName(column = "codice_fiscale")
    private String codiceFiscale;

    @CsvBindByName(column = "data_nascita")
    private String dataNascita;

    @CsvBindByName(column = "numero_sinistri")
    private Integer numeroSinistri;

}
