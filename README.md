# VetApp

Projede Java 8, Spring Boot 2.6.4 sürümleri kullanıldı. Spring bağımlılıkları`data-jpa`,`security`,`spring-boot-starter-web`,`thymeleaf`,`mysql`,`lombok`,`hibernate-validator`,`hibernate-search-orm`,`bootstrap`.

VetApp projesinde kullanıcı girişi yapanlar  hayvan sahibi ve hayvan kaydı oluşturulabiliyor. Hayvan sahipleri ve hayvanlar ayrı sayfalarda gösteriliyor. Profil sayfalarına bakılabiliyor. Her ikisi içinde düzenleme ve silme işlemleri yapılabiliyor. Silinen hayvan sahibine bağlı bütün hayvanlarda silinir. Hayvan sahiplerinin **n** adet hayvanı olabilir. Hayvanların **bir** adet sahibi olacak şekilde ayarlandı.

```java
@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private List<Animal> animals = new ArrayList<>();
```

```java
@ManyToOne(cascade = CascadeType.PERSIST)
@JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
private Person person;

```

Hayvan Sahiplerinin gösterildiği sayfada hem hayvan sahibi ismiyle hemde hayvan adıyla arama işlemi yapılabiliyor. Eğer arama işlemi yapılmazsa default olarak bütün hayvan sahipleri listelenir.

```java
@Query("Select distinct p from Person p left join Animal a on p.personId = a.person.personId where lower(p.nameSurname) like %:keyword% or lower(a.name) like %:keyword%")
List<Person> findByName(String keyword);
```

Keyword boş gelmesi halinde bütün liste dönüyor. Gelen kayword stringini lowercase yapmamın sebebi küçük büyük harf duyarlılığınından kurtulmak ve doğru sonuçların gösterilmesini sağlamak.

```java
@Override
public List<Person> listAllKeyword(String keyword) {
    if (keyword != null)
        return personRepository.findByName(keyword.toLowerCase());
    return personRepository.findAll();
}
```

# RUN

Proje dizininde terminalden alttaki kodu çalıştırarak projeyi ayağa kaldırabilirsiniz.

`./mvnw spring-boot:run`

# H2

H2 Java uygulamalarına gömülebilir veya istemci-sunucu modunda çalıştırabildiği için bunuda eklemek istedim. Ayarlar application.properties kısmına eklenmiştir. Tercih türüne göre iki veri tabanı yönetim sisteminden birini kullanabilirsiniz. 

# MySQL

Database kısmında MySQL kullanmak isterseniz ayarlar aşağıdaki kod bloğunda paylaştım. Gerekli bilgiler application.properties'de de mevcuttur. Hibernate sayesinde dto sınıfları ile veritabanına denk gelecek nesneler oluşturabilir ve üzerinde işlemler yapmamıza yardımcı olur.
```java
spring.datasource.url=jdbc:mysql://localhost:3306/vetapp
spring.datasource.username=root
spring.datasource.password=password
```

## **User Table**

Üye olan kullanıcılar tablosu. Id otomatik artan şekilde tasarlandı. Email benzersiz olması gerekiyor. İki farklı kullanıcı aynı maili kullanamaz. Password BCrypt ile şifrelenerek tabloya kayıt edilir. Role `ROLE_USER` default olarak tanımlanır. POJO kısmında gerekli kısıtlar tanımlanmıştır.

| id | int |
| --- | --- |
| email | varchar |
| password | varchar |
| role | varchar |

## Person Table

Person tablosu hayvan sahiplerinin tutulduğu tablodur. Id otomatik artan şekilde ayarlandı. nameSurname kısmı hayvan sahibinin isim soyisiminin tutulduğu yer. contactinformation iletişim bilgilerinin tutulduğu yer. telephone ve email hayvan sahibinin kişisel bilgileri içindir. date kayıt oluşturulan tarihtir.

| id | int |
| --- | --- |
| nameSurname | varchar |
| contactinformation | varchar |
| telephone | varchar |
| email  | varchar |
| date | datatime |

## Animal Table

Hayvan tanımı için karşılık gelen alanlar tür, cins, isim, yaş, açıklamadır. person_id kısmı sahibin idsinin tutulduğu alandır.

| id | int |
| --- | --- |
| name | varchar |
| description | varchar |
| genus | varchar |
| age | varchar |
| species | varchar |
| date | datatime |
| person_id | int |

# Thymeleaf

Thymeleaf görünüm katmanını kullanırken tasarımın daha düzgün gözükmesi için Bootstrap kullanmam gerekti. Kayıtlı kullanıcılar hayvan sahibi ve hayvan ekleyebilir. Düzenleme ve silme işlemlerini de kayıtlı kullanıcılar yapabilir. Hayvan sahibi ve hayvanların bilgilerini de kayıtlı kullanıcılar görüntüleyebilir. Giriş yapmayan kullanıcılar sadece `/`, `/register` ve `/login` sayfalarında dolaşma imkanı var. Giriş yaptıktan sonra diğer sayfalara erişim kazanır.

| URL | Amacı |
| --- | --- |
| / | Index sayfası |
| /register | Kullanıcının kayıt olacağı sayfa |
| /login | Kullanıcının giriş yapabileceği sayfa |
| /persons | Bütün hayvan sahiplerinin listelendiği alan.  |
| /persons/new | Yeni bir hayvan sahibi eklenebilir |
| /person/{id} | Hayvan sahibinin profil sayfası |
| /persons/edit/{id} | Daha önce eklenmiş hayvan sahibinin bilgilerini düzenleyebileceğiniz sayfa |
| /persons/delete/{id} | Seçilen hayvan sahibini silmemize yardımcı olan sayfa |
| /animals | Bütün hayvanların listelendiği yer. |
| /animals/new | Yeni bir hayvan eklenebilecek olan sayfa |
| /animal/{id} | Hayvan profil sayfası |
| /animals/edit/{id} | Hayvan bilgilerinin düzenlenme imkanı sağlayan sayfa |
| /animals/delete/{id} | Hayvanı silme imkanı sağlayan sayfa |
| /error | Geçersiz url girildiğinde ekrana gelecek olan hata sayfası |

# Hibernate

Java sınıflarından veritabanı tablolarına dönüşümü sağlamak ve Java veri tiplerinden SQL veri tiplerine dönüşümünü gerçekleştirmek için Hibernate kullandım. Ayrıcı veri sorgulama ve veri çekme işlemlerinde de kolaylık sağlar.

# Spring Security

Spring framework kullanılarak geliştirilen doğrulama, yetkilendirme, şifreleme ve CSRF gibi güvenlik önlemleri sağlayan, Spring platformunda yer alan bir projedir. VetApp projesinde tek tip kullanıcı rolü mevcuttur. Login olmayan kullanıcı sadece `/`, `/register` ve `/login` sayfalarına girme yetkisi mevcuttur. Login olan kullanıcı uygulamada ki diğer özellikleri kullanabilir.

# Lombok

Lombok, VetApp uygulamasını geliştirirken daha az ve daha temiz kod yazmamı sağlayan, okunabilirliği kolaylaştıran kütüphanedir. POJO’larda getter, setter gibi methodları annotationlar sayesinde daha okunaklı hale getiriyor.

# Test

JUnit kullanılarak UserRepository, PersonRepository, AnimalRepository birim testleri gerçekleştirilmiştir.
