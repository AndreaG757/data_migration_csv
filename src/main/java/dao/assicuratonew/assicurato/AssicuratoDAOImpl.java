package dao.assicuratonew.assicurato;

import dao.AbstractMySQLDAO;
import model.Assicurato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AssicuratoDAOImpl extends AbstractMySQLDAO {

    public void insertAssicurato(Assicurato input) throws Exception {

        if (isNotActive())
            throw new Exception("Connessione non attiva in insert() di AssicuratoDAOImpl. Impossibile effettuare operazioni DAO.");

        if (input == null)
            throw new Exception("Valore di input non ammesso.");

        try(PreparedStatement pstmt = connection.prepareStatement("INSERT INTO ASSICURATO (NOME, COGNOME, DATA_NASCITA, NUMERO_SINISTRI, CODICE_FISCALE) VALUES (?, ?, ?, ?, ?);")) {

            pstmt.setString(1, input.getNome());
            pstmt.setString(2, input.getCognome());
            pstmt.setDate(3, new java.sql.Date(parseDate(input.getDataNascita()).getTime()));
            pstmt.setInt(4, input.getNumeroSinistri());
            pstmt.setString(5, input.getCodiceFiscale());
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    public void insertNotProcessed(Assicurato input) throws Exception {

        if (isNotActive())
            throw new Exception("Connessione non attiva in insert() di AssicuratoDAOImpl. Impossibile effettuare operazioni DAO.");

        if (input == null)
            throw new Exception("Valore di input non ammesso.");

        try(PreparedStatement pstmt = connection.prepareStatement("INSERT INTO NOT_PROCESSED (CODICE_FISCALE, OLD_ID) VALUES (?, ?);")) {

            pstmt.setString(1, input.getCodiceFiscale());
            pstmt.setLong(2, input.getId());
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    private Date parseDate(String data) throws Exception {
        return new SimpleDateFormat("yyyy-MM-dd").parse(data);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
