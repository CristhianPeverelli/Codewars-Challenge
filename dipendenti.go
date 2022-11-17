/* Per implementare un codice relativo a questo problema, ho deciso di usare una struttura ad
 * albero, però con una radice "astratta", poichè una azienda può avere più capi tutti dello stesso livello massimo.
 * Quindi l'albero inizierà con una radice di "Azienda", la quale avrà come subordinati i capi dell'azienda.
 * Ho poi usato una struct con all'interno il nome del dipendente, una slice con all'interno tutti i puntatori
 * ai sottoposti, ed un puntatore al supervisore (padre)
 */

package main

import "fmt"

type Dipendente struct{
  nome string
  subOrdinati []*Dipendente
  supervisore *Dipendente
}

func main(){
  var capi []*Dipendente
  dip := newDipendente("Mauro")
  dip = aggiungiSubordinato(dip, newDipendente("Maurizio"))
  dip = aggiungiSubordinato(dip, newDipendente("Laura"))
  capi = append(capi, dip)
  dip = newDipendente("Piera")
  dip2 := newDipendente("Lucia")
  dip3 := newDipendente("Lorenza")
  dip2 = aggiungiSubordinato(dip2, dip3)
  dip = aggiungiSubordinato(dip, dip2)
  dip = aggiungiSubordinato(dip, newDipendente("Rebecca"))
  capi = append(capi, dip)
  azienda := creaAzienda(capi)

  fmt.Print(quantiSenzaSubordinati(azienda),"\n")
  supervisore(dip)
  stampaImpiegatiSopra(dip3)
  stampaAzienda(azienda)

}

func newDipendente(n string) *Dipendente{
  d := Dipendente{nome: n}
  return &d
}

func creaAzienda(capi []*Dipendente) *Dipendente{
  d := newDipendente("Azienda") //d è la radice dell'albero, è solo un dipendente astratto
  for _,c := range capi{
    d = aggiungiSubordinato(d,c)
  }
  return d
}

func aggiungiSubordinato(capo,sub *Dipendente) *Dipendente{
  sub.supervisore = capo
  capo.subOrdinati = append(capo.subOrdinati, sub)
  return capo
}

func stampaCapi(astratto *Dipendente){
  fmt.Print("I capi azienda sono: ")
  for _,sub := range astratto.subOrdinati{
    fmt.Print(sub.nome," ")
  }
  fmt.Print("\n")
}

func stampaSubordinati(dip *Dipendente){
  fmt.Print(dip.nome," ha come subordinati: ")
  for _,sub := range dip.subOrdinati{
    fmt.Print(sub.nome," ")
  }
  fmt.Print("\n")
}

func quantiSenzaSubordinati(azienda *Dipendente) (conta int){
  return contaSubordinatiRic(azienda,0)
}

func contaSubordinatiRic(dipDaControllare *Dipendente, conta int) int{
  if len(dipDaControllare.subOrdinati) == 0 {
    fmt.Print(dipDaControllare.nome," non ha subordinati.\n")
    conta++
  }else{
    for _,s := range dipDaControllare.subOrdinati{
      conta = contaSubordinatiRic(s,conta)
    }
  }
  return conta
}

func supervisore(dip *Dipendente) *Dipendente{
  //Stampa inserita solo per testing
  if dip.supervisore.nome == "Azienda"{
    fmt.Println(dip.nome," è un capo di rango massimo.")
  }else{
    fmt.Println("Il supervisore di ",dip.nome," è ",dip.supervisore.nome,"\n")
  }
  return dip.supervisore
}

func stampaImpiegatiSopra(dip *Dipendente){
  if(dip.supervisore.nome != "Azienda"){
    fmt.Print(dip.nome," ha come impiegati di grado superiore: ")
    fmt.Print(dip.supervisore.nome," ")
    impiegatiSopraRic(dip.supervisore)
  }else{
    fmt.Print(dip.nome," non ha impiegati di grado superiore.")
  }
  fmt.Print("\n")
}

func impiegatiSopraRic(dip *Dipendente){
  if(dip.supervisore.nome != "Azienda"){
    fmt.Print(dip.supervisore.nome," ")
    impiegatiSopraRic(dip.supervisore)
  }
}

func stampaAzienda(azienda *Dipendente){
  stampaCapi(azienda)
  if len(azienda.subOrdinati) != 0 {
    fmt.Print("Tutti gli altri dipendenti sono: ")
    for _,capo := range azienda.subOrdinati{  //Per evitare ripetizione stampa capi
      for _,dip := range capo.subOrdinati{
        stampaSubordinatiRic(dip)
      }
    }
  }
  fmt.Print("\n")
}

func stampaSubordinatiRic(dip *Dipendente){
  fmt.Print(dip.nome," ")
  if len(dip.subOrdinati) != 0{
    for _,d := range dip.subOrdinati{
      stampaSubordinatiRic(d)
    }
  }
}
