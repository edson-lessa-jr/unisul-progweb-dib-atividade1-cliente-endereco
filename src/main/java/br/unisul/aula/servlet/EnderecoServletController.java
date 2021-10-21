package br.unisul.aula.servlet;

import br.unisul.aula.banco.EnderecoDAO;
import br.unisul.aula.dtocliente.EnderecoDTO;
import br.unisul.aula.modelo.Endereco;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EnderecoServletController", value = "/endereco")
public class EnderecoServletController extends HttpServlet{
	
	private final Gson gson = new Gson();
	private final EnderecoDAO enderecoDAO = new EnderecoDAO();
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");

        List<Endereco> enderecoList = enderecoDAO.findAll();
        List<EnderecoDTO> dtos = new ArrayList<>();
        for (int i = 0; i < enderecoList.size(); i++) {
            EnderecoDTO dto = new EnderecoDTO(enderecoList.get(i));
            dtos.add(dto);
        }
        String enderecoJson = gson.toJson(dtos);
        response.getWriter().println(enderecoJson);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        EnderecoDTO enderecoDTO = gson.fromJson(reader, EnderecoDTO.class);
        Endereco endereco = enderecoDTO.converterParaEndereco();
        enderecoDAO.insert(endereco);
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        EnderecoDTO enderecoDTO = gson.fromJson(reader, EnderecoDTO.class);
        Endereco endereco;
        if (request.getParameter("id") != null) {
            Long id = Long.parseLong(request.getParameter("id"));
            Endereco enderecoAntigo = enderecoDAO.findById(id);
            endereco = enderecoDTO.converterParaEndereco(enderecoAntigo);
        } else {
            endereco = enderecoDTO.converterParaEndereco();
        }
        enderecoDAO.update(endereco);
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id;
        if (request.getParameter("id") != null) {
            id = Long.parseLong(request.getParameter("id"));
        } else {
            BufferedReader reader = request.getReader();
            EnderecoDTO enderecoDTO = gson.fromJson(reader, EnderecoDTO.class);
            id = enderecoDTO.getIdEndereco();
        }
        System.out.println(id);
        enderecoDAO.remove(id);
    }
}
