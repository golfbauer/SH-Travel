

function myfunction() {
    var reisepunkt = {id:112, laengengrad:53.8662, breitengrad:10.376, discriminator:"Attraktion",nutzeremail:"numeric@generated.bla", name:"hammelburg"}

    document.getElementById("id").innerText = reisepunkt.id;
    document.getElementById("laengengrad").innerText = reisepunkt.laengengrad;
    document.getElementById("breitengrad").innerText = reisepunkt.breitengrad;
    document.getElementById("discriminator").innerText = reisepunkt.discriminator;
    document.getElementById("nutzeremail").innerText = reisepunkt.nutzeremail;
    document.getElementById("name").innerText = reisepunkt.name;
}
