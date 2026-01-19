package kz.com.ars.message_system.module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "authorities")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
    @Id
    private Long id;
    @Column("name")
    private String name;
}
