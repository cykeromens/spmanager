package com.cykeromens.model.user;

import com.cykeromens.model.DomainEntity;
import com.cykeromens.model.contact.ContactDetails;
import com.cykeromens.model.image.Image;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by omens on 8/1/15.
 */
@Entity
@Table(name = "T_USER")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User extends DomainEntity{

    @Column(name = "TITLE")
    private String title;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "USERNAME", unique = true)
    private String username;
    @Column(name = "PASSWORD")
    private String password;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTACT_ID")
    private ContactDetails contactDetails;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "J_USER_PHOTOS",
            joinColumns = {@JoinColumn(name = "USER_ID",unique = true)},
            inverseJoinColumns = {@JoinColumn(name = "IMAGE_ID")}
    )
    private Collection<Image> photos;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(column = @Column(name = "GENDER"),name = "gender"),
            @AttributeOverride(column = @Column(name = "DATE_OF_BIRTH"),name = "dateOfBirth"),
    })
    private BioData bioData;

   @Column(name = "ROLE")
    private Enum role;

    public User() {
    }

    public User(String title, String firstName, String lastName, String username, String password, String email, String businessPhoneNumber) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = setPassWordHash(password);
        this.contactDetails.setEmail(email);
        this.contactDetails.setBusinessPhoneNumber(businessPhoneNumber);
        this.setCreatedDate(LocalDateTime.now());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = setPassWordHash(password);
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public Collection<Image> getPhotos() {
        return photos;
    }

    public void setPhotos(Collection<Image> photos) {
        this.photos = photos;
    }

    public BioData getBioData() {
        return bioData;
    }

    public void setBioData(BioData bioData) {
        this.bioData = bioData;
    }

    public Enum getRole() {
        return role;
    }

    public void setRole(Enum role) {
        this.role = role;
    }

    @Transient
    private final String setPassWordHash(String password){
        return new BCryptPasswordEncoder().encode(password);
    }
}
