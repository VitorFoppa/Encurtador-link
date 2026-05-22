package com.encurtador.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.encurtador.model.LinkModel;
import com.encurtador.repository.LinkRepository;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    private final String chars =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private final Random random = new Random();

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public String gerarCodigo() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        return sb.toString();
    }

    public LinkModel salvar(LinkModel linkModel) {

        String url = linkModel.getUrl();

        if (!url.startsWith("http://")
                && !url.startsWith("https://")) {

            url = "https://" + url;
        }

        linkModel.setUrl(url);

        String codigo = gerarCodigo();

        linkModel.setCodigo(codigo);

        linkModel.setCriadoEm(LocalDateTime.now());

        if (linkModel.getAcessos() == null) {
            linkModel.setAcessos(0);
        }

        return linkRepository.save(linkModel);
    }

    public LinkModel buscarPorCodigo(String codigo) {

        return linkRepository.findByCodigo(codigo)
                .orElse(null);
    }

    public List<LinkModel> listarTodos() {

        return linkRepository.findAll();
    }

    public void registrarAcesso(LinkModel link) {

        link.setAcessos(link.getAcessos() + 1);

        link.setUltimoAcesso(LocalDateTime.now());

        linkRepository.save(link);
    }
}