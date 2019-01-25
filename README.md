# Parqueadero-Ceiba
change the type-ID for ObjectId id
#GET
@RequestMapping(value = "/", method = RequestMethod.GET)
public List<VehiculoModel> getAllVehiculos() {
  return repositorio.findAll();
}

@RequestMapping(value = "/{id}", method = RequestMethod.GET)
public Pets getVehiculoById(@PathVariable("id") ObjectId id) {
  return repository.findBy_id(id);
}
#PUT
@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
public void modifyVehiculoById(@PathVariable("id") ObjectId id, @Valid @RequestBody VehiculoModel vehiculoModel) {
  vehiculoModel.getId(id);
  repositorio.save(pets);
}
#DELETE
@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
public void deleteVehiculo(@PathVariable ObjectId id) {
  repositorIO.delete(repositorIO.findBy_id(id));
}
#POST
@RequestMapping(value = "/", method = RequestMethod.POST)
public VehiculoModel createPet(@Valid @RequestBody VehiculoModel vehiculoModel) {
  vehiculoModel.getId(ObjectId.get());
  repositorio.save(pets);
  return vehiculoModel;
}
