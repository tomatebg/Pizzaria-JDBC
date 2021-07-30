package pizzaria.model.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pizzaria.model.Pizza;

public class PizzaDao {

    private ConnectionFactory connectionFactory;
    private final String insert = "insert into pizzas (id,id_pedido, forma, centimetro_quadrado, area, raio, id_sabor1, id_sabor2, preco) values (?,?,?,?,?,?,?,?,?)";
    private final String select = "select * from pizzas";
    private final String selectWhere = "select * from pizzas where id =?";
    private final String update = "update pizzas set forma=?, centimetro_quadrado=?, area=?, raio=?, id_sabor1=?, id_sabor2=?, preco=? WHERE id=?";
    private final String delete = "delete from pizzas WHERE id=?";

    public PizzaDao(ConnectionFactory conFactory) {
        this.connectionFactory = conFactory;
    }

    public void inserir(Pizza pizza) {
        Connection connection = connectionFactory.getConnection();
        try {
            PreparedStatement stmtAdiciona = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmtAdiciona.setString(1, pizza.getId());
            stmtAdiciona.setString(2, pizza.getPedido());
            stmtAdiciona.setInt(3, pizza.getNumberForma());
            stmtAdiciona.setBoolean(4, pizza.isIsMetricaCmQuadrado());
            stmtAdiciona.setDouble(5, pizza.getArea());
            stmtAdiciona.setDouble(6, pizza.getLadoOuRaio());
            stmtAdiciona.setString(7, pizza.getSabor1());
            stmtAdiciona.setString(8, pizza.getSabor2());
            stmtAdiciona.setDouble(9, pizza.getPrecoTotal());
            // executa
            stmtAdiciona.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Pizza> getLista() throws SQLException {
        Connection connection = connectionFactory.getConnection();
        ResultSet rs = null;
        PreparedStatement stmtLista = connection.prepareStatement(select);
        try {
            rs = stmtLista.executeQuery();
            List<Pizza> pizzas = new ArrayList();
            while (rs.next()) {
                String id = rs.getString("id");
                String id_pedido = rs.getString("id_pedido");
                Integer forma = rs.getInt("forma");
                Boolean isMetricaCmQuadrado = rs.getBoolean("centimetro_quadrado");
                Double area = rs.getDouble("area");
                Double ladoOuRaio = rs.getDouble("raio");
                String id_sabor1 = rs.getString("id_sabor1");
                String id_sabor2 = rs.getString("id_sabor2");
                Double precoTotal = rs.getDouble("preco");
                pizzas.add(new Pizza(id, id_pedido, forma, isMetricaCmQuadrado,
                        area, ladoOuRaio, id_sabor1, id_sabor2,
                        precoTotal));
            }
            return pizzas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            rs.close();
            stmtLista.close();
        }
    }

    public List<Pizza> getListaDoPedido(String idpedido) throws SQLException {
        Connection connection = connectionFactory.getConnection();
        ResultSet rs = null;
        PreparedStatement stmtLista = connection.prepareStatement(selectWhere);
        try {
            stmtLista.setString(1, idpedido);
            rs = stmtLista.executeQuery();
            List<Pizza> pizzas = new ArrayList();
            while (rs.next()) {
                String id = rs.getString("id");
                String id_pedido = rs.getString("id_pedido");
                Integer forma = rs.getInt("forma");
                Boolean isMetricaCmQuadrado = rs.getBoolean("centimetro_quadrado");
                Double area = rs.getDouble("area");
                Double ladoOuRaio = rs.getDouble("raio");
                String id_sabor1 = rs.getString("id_sabor1");
                String id_sabor2 = rs.getString("id_sabor2");
                Double precoTotal = rs.getDouble("preco");
                pizzas.add(new Pizza(id, id_pedido, forma, isMetricaCmQuadrado,
                        area, ladoOuRaio, id_sabor1, id_sabor2,
                        precoTotal));
            }
            return pizzas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            rs.close();
            stmtLista.close();
        }
    }

    public void atualizar(Pizza pizza) throws SQLException {
        Connection connection = connectionFactory.getConnection();
        PreparedStatement stmtAtualiza = connection.prepareStatement(update);
        try {
            stmtAtualiza.setInt(1, pizza.getNumberForma());
            stmtAtualiza.setBoolean(2, pizza.isIsMetricaCmQuadrado());
            stmtAtualiza.setDouble(3, pizza.getArea());
            stmtAtualiza.setDouble(4, pizza.getLadoOuRaio());
            stmtAtualiza.setString(5, pizza.getSabor1());
            stmtAtualiza.setString(6, pizza.getSabor2());
            stmtAtualiza.setDouble(7, pizza.getPrecoTotal());
            stmtAtualiza.setString(8, pizza.getId());
            stmtAtualiza.executeUpdate();
        } finally {
            stmtAtualiza.close();
        }

    }

    public void excluirLista(List<Pizza> pizzas) throws SQLException {
        for (Pizza pizza : pizzas) {
            excluir(pizza);
        }
    }

    public void excluir(Pizza pizza) throws SQLException {
        Connection connection = connectionFactory.getConnection();
        PreparedStatement stmtExcluir;
        stmtExcluir = connection.prepareStatement(delete);
        try {
            stmtExcluir.setString(1, pizza.getId());
            stmtExcluir.executeUpdate();
        } finally {
            stmtExcluir.close();
        }

    }

}
