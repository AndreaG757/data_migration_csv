package service;

import connection.MyConnection;
import dao.Constants;
import dao.assicuratonew.assicurato.AssicuratoDAOImpl;
import model.Assicurato;
import utility.ParseCsv;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AssicuratoServiceImpl {

    private AssicuratoDAOImpl assicuratoDAOImpl = new AssicuratoDAOImpl();

    public void dataMigrator() throws Exception {

        List<Assicurato> assicurati = ParseCsv.parseAssicurato();

        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            assicuratoDAOImpl.setConnection(connection);

            for (Assicurato assicuratoItem : assicurati) {
                System.out.println(assicuratoItem);
                if (validate(assicuratoItem))
                    assicuratoDAOImpl.insertAssicurato(assicuratoItem);
                else
                    assicuratoDAOImpl.insertNotProcessed(assicuratoItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean validate(Assicurato input) {

        if (input.getNumeroSinistri() == null) {
            return false;
        } else if (input.getNome().isEmpty() || input.getNome().equals("")) {
            return false;
        } else if (input.getCognome().isEmpty() || input.getCognome().equals("")) {
            return false;
        } else if (input.getCodiceFiscale().isEmpty() || input.getCodiceFiscale().equals("")) {
            return false;
        } else if (input.getDataNascita() == null || input.getDataNascita().equals("")) {
            return false;
        }

        return true;

    }

}
