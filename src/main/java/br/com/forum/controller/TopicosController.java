package br.com.forum.controller;

import br.com.forum.Exception.CursoNotFoundException;
import br.com.forum.Exception.TopicoNotFoundException;
import br.com.forum.Exception.UsuarioNotFoundException;
import br.com.forum.model.DTO.DetalhesDoTopicoDto;
import br.com.forum.model.DTO.TopicoDTO;
import br.com.forum.model.Topico;
import br.com.forum.model.form.AtualizacaoTopicoForm;
import br.com.forum.model.form.TopicoForm;
import br.com.forum.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/topico")
public class TopicosController {

    @Autowired
    private TopicoService topicoService;

    @GetMapping
    public ResponseEntity<List<TopicoDTO>> lista(String nomeCurso) {

        if (nomeCurso == null) {
            return ResponseEntity.status(HttpStatus.OK).body(TopicoDTO.converter(topicoService.listar()));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(TopicoDTO.converter(topicoService.buscarPorCurso(nomeCurso)));
        }

    }

    @GetMapping(value = "/paginado")
    public ResponseEntity<Page<TopicoDTO>> buscarTopicosPaginados(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10) Pageable pageable){
         Page<Topico> paginaDeTopicos = topicoService.buscarTopicosPaginados(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(TopicoDTO.converter(paginaDeTopicos));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DetalhesDoTopicoDto> buscar(@PathVariable Long id) throws TopicoNotFoundException {
        DetalhesDoTopicoDto detalhesDoTopicoDto = new DetalhesDoTopicoDto(topicoService.buscarPorId(id));
        return ResponseEntity.status(HttpStatus.OK).body(detalhesDoTopicoDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriBuilder)
            throws CursoNotFoundException, UsuarioNotFoundException {

        Topico topicoCadastrado = topicoService.cadastrar(topicoForm);

        URI uri = uriBuilder.path("/topico/{id}").buildAndExpand(topicoCadastrado.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicoDTO(topicoCadastrado));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> atualizar( @PathVariable Long id,@RequestBody @Valid AtualizacaoTopicoForm topicoForm) throws TopicoNotFoundException {

        Topico topicoAtualizado = topicoService.atualizar(topicoForm, id);

        return ResponseEntity.status(HttpStatus.OK).body(new TopicoDTO(topicoAtualizado));
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) throws TopicoNotFoundException {
        topicoService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}