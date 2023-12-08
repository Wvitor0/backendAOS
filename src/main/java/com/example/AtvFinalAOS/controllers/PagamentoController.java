import com.example.AtvFinalAOS.models.Categoria;

import com.example.AtvFinalAOS.models.Pagamento;
import com.example.AtvFinalAOS.repository.CategoriaRepository;
import com.example.AtvFinalAOS.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @PostMapping("/pagamento")
    public Pagamento createCategoria(@RequestBody Pagamento newPagamento){
        return pagamentoRepository.save(newPagamento);
    }

    @GetMapping("/pagamento")
    public List<Pagamento> getPagamento(){
        return pagamentoRepository.findAll();
    }

    @GetMapping("/pagamento/key={id}")
    public Pagamento getPagamentoById(@PathVariable Long id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pagamento não encontrado com o id: " + id));
    }
}