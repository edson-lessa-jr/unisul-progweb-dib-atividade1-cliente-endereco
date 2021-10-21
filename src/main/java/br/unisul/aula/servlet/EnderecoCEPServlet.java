package br.unisul.aula.servlet;

import br.unisul.aula.banco.EnderecoDAO;
import br.unisul.aula.dtos.EnderecoDTO;
import br.unisul.aula.modelo.Endereco;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EnderecoCEPServlet", value = "/enderecocep")
public class EnderecoCEPServlet extends HttpServlet {
    private final Gson gson = new Gson();
    private final EnderecoDAO enderecoDAO = new EnderecoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        Endereco endereco = consultarPorCEP(request);
        EnderecoDTO dto = new EnderecoDTO(endereco);

        String enderecoJson = gson.toJson(dto);
        response.getWriter().println(enderecoJson);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        redirecionarParaEnderecoServlet(request, response);

    }

    private Endereco consultarPorCEP(HttpServletRequest request) {
        Integer cep = Integer.parseInt(request.getParameter("cep"));
        return enderecoDAO.findByCep(cep);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        redirecionarParaEnderecoServlet(request, response);
    }

    private void redirecionarParaEnderecoServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Endereco endereco = consultarPorCEP(request);
        RequestDispatcher alterarPorID = request.getRequestDispatcher("endereco?id=" + endereco.getId());
        alterarPorID.forward(request, response);
    }
}
