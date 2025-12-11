package kz.com.nurzandars.message_system.module;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "chat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    @NotNull
    @Size(min = 1, max = 50,message = "Name must be between 1 and 50 characters")
    private String name;
    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @NotNull(message = "CreatedBy cannot be null")
    private User createdBy;
    @OneToMany(mappedBy = "chat", cascade = CascadeType.REMOVE)
    private List<Message> messages;
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private List<ChatMember> members;
}
