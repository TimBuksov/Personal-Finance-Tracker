package dev.timbuksov;
import java.math.BigDecimal;
import java.util.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
@RestController @RequestMapping("/api") public class FinanceController {
 private final TransactionRepository repo;
 public FinanceController(TransactionRepository repo){this.repo=repo;}
 @GetMapping("/transactions") public List<Transaction> list(){return repo.findAllByOrderByDateDescIdDesc();}
 @PostMapping("/transactions") @ResponseStatus(HttpStatus.CREATED) public Transaction add(@Valid @RequestBody Transaction incoming){incoming.id=null;return repo.save(incoming);}
 @PutMapping("/transactions/{id}") public Transaction edit(@PathVariable Long id,@Valid @RequestBody Transaction incoming){
  Transaction entity=repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found"));
  entity.description=incoming.description;entity.category=incoming.category;entity.amount=incoming.amount;entity.date=incoming.date;entity.kind=incoming.kind;return repo.save(entity);
 }
 @DeleteMapping("/transactions/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){
  if(!repo.existsById(id))throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found");repo.deleteById(id);
 }
 @GetMapping("/summary") public Map<String,Object> summary(@RequestParam(required=false) String month){
  if(month!=null&&!month.matches("\\d{4}-\\d{2}"))throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Use YYYY-MM");
  BigDecimal income=BigDecimal.ZERO,expense=BigDecimal.ZERO;Map<String,BigDecimal> categories=new TreeMap<>();
  for(Transaction t:repo.findAll()){if(month!=null&&!t.date.toString().startsWith(month))continue;
   if(t.kind==Transaction.Kind.INCOME)income=income.add(t.amount);else{expense=expense.add(t.amount);categories.merge(t.category,t.amount,BigDecimal::add);}}
  return Map.of("income",income,"expenses",expense,"balance",income.subtract(expense),"byCategory",categories);
 }
}
