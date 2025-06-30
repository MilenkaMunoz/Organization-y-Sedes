# Estructura de Base de Datos MongoDB para Sistema JASS (Microservicios)
<!--horizontal divider(gradiant)-->
<img src="https://user-images.githubusercontent.com/73097560/115834477-dbab4500-a447-11eb-908a-139a6edaec5c.gif">

### Colección: organizaciones

```javascript

{
  "_id": ObjectId("..."),
  "organization_code": "JASS001",
  "organization_name": "JASS Rinconada de Conta",
  "legal_representative": "Juan Pérez",
  "address": "Av. Principal 123",
  "phone": "987654321",
  "status": "ACTIVE",                     
  "created_at": ISODate("2023-01-10T10:00:00Z"),
  "updated_at": ISODate("2023-01-10T10:00:00Z")
}

```
### Colección: zones

```javascript
{
  "_id": ObjectId("..."),
  "organization_id": ObjectId("..."),  
  "zone_code": "ZN0001",
  "zone_name": "Rinconada de Conta",
  "description": "Zona centro poblado de Rinconada de Conta",
  "status": "ACTIVE",                     
  "created_at": ISODate("2023-01-10T10:05:00Z")
}

```

### Colección: streets

```javascript

{
  "_id": ObjectId("..."),
  "zone_id": ObjectId("..."),            
  "street_code": "CAL001",
  "street_name": "Calle Los Pinos",
  "street_type": "CALLE",                 
  "status": "ACTIVE",                     
  "created_at": ISODate("2023-01-10T10:10:00Z")
}
```
### Relacion

```javascript
organizations (1) ──── (N) zones
zones         (1) ──── (N) streets

