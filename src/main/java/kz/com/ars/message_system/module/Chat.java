package kz.com.ars.message_system.module;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table(name = "chat")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    @Id
    private Long id;
    @Column("name")
    private String name;
    @Column("created_by")
    private Long createdBy;
}
