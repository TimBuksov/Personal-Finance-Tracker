package dev.timbuksov;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Entity @Table(name="transactions") public class Transaction {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @NotBlank public String description;
 @NotBlank public String category;
 @NotNull @DecimalMin("0.01") @Column(precision=12,scale=2) public BigDecimal amount;
 @NotNull public LocalDate date;
 @NotNull @Enumerated(EnumType.STRING) public Kind kind;
 public enum Kind {INCOME,EXPENSE}
}
