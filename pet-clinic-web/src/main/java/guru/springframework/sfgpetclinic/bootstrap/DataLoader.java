package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//implementando CommandLineRunner e marcandolo come component questa classe verrà runnata da Spring automaticamente
// quando il contesto Spring si sarà caricato (viene difatti chiamato il metodo run).
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    //i due service vengono iniettati mediante constructor injection, ma è necessario
    // annotare i singoli service con @Service affinchè la component Scan li individui come beans
    //da inserire nel lifecycle di Spring. La component scan è definita implicitamente a partire dal package
    //guru.springframework.sfgpetclinic della pet-clinic-web e si estende ai sotto package.
    //La component scan funziona però anche sul progetto pet-clinic-data a patto che venga mantenuto
    //esattamente lo stesso nome package  di prima, dato che nel packaging finale della app
    //il progetto pet-clinic-data verrà tirato dentro come dipendenza di pet-clinic-web
    // portando dentro quindi anche le classi compilate relative a pet-clinic-data, mantenendo come
    //package guru.springframework.sfgpetclinic.
    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }


    @Override
    public void run(String... args) throws Exception {

        Owner owner1=new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");

        ownerService.save(owner1);

        Owner owner2=new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");

        ownerService.save(owner2);

        System.out.println("Loaded Owners....");


        Vet vet1=new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2=new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);

        System.out.println("Loaded Vets....");

    }
}
