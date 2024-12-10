# Freemarker Şablon Oluşturma Rehberi

## 1. Genel Kurallar
* Freemarker şablonları, JSON formatına uygun şekilde oluşturulmalıdır.
* Boşluklara dikkat edilmeli ve her satır okunabilir olmalıdır.
* Zorunlu alanlar eksiksiz şekilde belirtilmelidir.
* Dinamik içerikler ${alanAdi} şeklinde tanımlanır.
* Döngüler ve şart blokları için Freemarker’ın kontrol direktifleri kullanılır.


## 2. Şablon Yapıları

#### `2.1. Düz Nesne (Object) Yapısı`

Düz nesne, bir anahtar-değer çiftini temsil eder. Şablon örneği:

```json
{
  "fullName": "${fullName}",
  "age": ${age}
}
```

* ${fullName}: Dinamik bir alan, değer daha sonra işleme sırasında doldurulur.
* ${age}: Sayısal bir alan için kullanılır.


#### `2.2. Dizi (Array) Yapısı`

Bir dizi, birden fazla nesne içerir. #list direktifi kullanılarak tanımlanır.

Şablon Örneği:
```json
{
  "userList": [
  <#list users as user>
    {
      "fullName": "${user.name}",
      "yearsOld": ${user.age}
    }<#if user_has_next>,</#if>
  </#list>
  ]
}
```
* <#list users as user>: users dizisini döngüye sokar ve her bir eleman için user değişkeni tanımlar.
* ${user.name}: Dizinin her elemanından name alanını getirir.
* <#if user_has_next>,</#if>: Son elemandan sonra virgül koymamak için kullanılır.


#### `2.3. Tarih Formatı`
Tarih alanları için belirli bir format kullanılır. Tarihler dinamik olarak işlenir ve formatları dönüştürülebilir.

Şablon Örneği:
```json
{
  "dateOfBirth": "${dateOfBirth?string('MM/yyyy/dd')}"
}
```
* ?string('MM/yyyy/dd'): Tarihi MM/yyyy/dd formatına dönüştürür.
* Tarih alanlarında her zaman ISO-8601 standardında tarih gönderilmeli, örneğin: 2023-05-20.


#### `2.4. Şartlı (Conditional) Alanlar`

Belli bir koşula bağlı olarak bazı alanlar işlenir.

Şablon Örneği:
```json
{
  "fullName": "${fullName}",
  "isAdult": <#if age >= 18>true<#else>false</#if>
}
```
* <#if> ve <#else>: Koşullu ifadeleri işler.
* Eğer age 18 veya daha büyükse, isAdult alanı true olur, aksi halde false.


#### `2.5. Sayısal Alanlar`
Sayısal değerler, herhangi bir formatlama yapılmadan doğrudan işlenir. Örneğin:

```json
{
  "price": ${price}
}
```

Eğer sayı yuvarlanacak veya formatlanacaksa:
```json
{
  "price": "${price?string['#.##']}"
}
```
* ?string['#.##']: Sayıyı iki ondalık basamakla sınırlar.


#### `2.6. Özel Dönüstürmeler`

Bazı durumlarda, Freemarker’ın sağladığı varsayılan işlevler yeterli olmayabilir. Bu gibi durumlarda, geliştiriciler özel dönüştürme işlemleri (Custom Converter) yazarak ihtiyaçlarınıza uygun çözüm üretebilir.

Şablon Örneği:
```json
{
  "formattedDate": "${customDateFormat(dateOfBirth, 'MM/yyyy/dd')}"
}
```
* dateOfBirth: İşlenecek veri.
* 'MM/yyyy/dd': İstenen özel format.


## 3. Örnek Tam Şablon

```json
{
  "userList": [
  <#list users as user>
    {
      "fullName": "${user.name}",
      "yearsOld": ${user.age},
      "birthDate": "${user.birthDate?string('MM/yyyy/dd')}",
      "isAdult": <#if user.age >= 18>true<#else>false</#if>
    }<#if user_has_next>,</#if>
  </#list>
  ],
  "metadata": {
    "totalUsers": ${users?size},
    "generationDate": "${.now?string('yyyy-MM-dd HH:mm:ss')}"
  }
}
```


## 4. Freemarker Hakkında Kaynaklar

* Freemarker Resmi Dokümantasyonu: [https://freemarker.apache.org/docs/](https://freemarker.apache.org/docs/)
* Freemarker Template Language Reference: [Template Language Reference](https://freemarker.apache.org/docs/ref.html)
* Tarih ve Sayı Formatlama: [Built-in Reference](https://freemarker.apache.org/docs/ref_builtins_date.html)
