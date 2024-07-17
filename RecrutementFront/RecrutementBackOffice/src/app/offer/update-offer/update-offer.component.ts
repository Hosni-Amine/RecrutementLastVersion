import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Categorie } from 'src/app/models/categorie.model';
import { Offer } from 'src/app/models/offer.model';
import { CategorieService } from 'src/app/service/categorie.service';
import { FileService } from 'src/app/service/file.service';
import { OfferService } from 'src/app/service/offer.service';

@Component({
  selector: 'app-add-offer',
  templateUrl: './update-offer.component.html',
  styleUrls: ['./update-offer.component.scss']
})
export class UpdateOfferComponent implements OnInit{
  offerForm: FormGroup;
  offerId: number | null = null; // Change type to number
  uploadProgress:number |null=null;
  imagePreview:string|ArrayBuffer|null=null;
  fileToUpload:File | null=null;
  categories: Categorie[] = [];
 /* uploadProgress:number |null=null;
  imagePreview:string|ArrayBuffer|null=null;
  fileToUpload:File | null=null;*/
  constructor(
    private fb: FormBuilder,
    private offerService: OfferService,
    private router: Router,
    private route: ActivatedRoute,
    private fileService:FileService,
    private categorieService:CategorieService

  ) {
    this.offerForm = this.fb.group({
      titre: ['', Validators.required],
      description: ['', Validators.required],
      qualification: ['', Validators.required],
      avantages: ['', Validators.required],
      localisation: ['', Validators.required],
      niveauExperience: ['', Validators.required],
      nombrePostes: [1, Validators.min(1)],
      exigencesLangue: ['', Validators.required],
      typeEmploi: ['', Validators.required],
      dateLimite: ['', Validators.required],
      categorieId: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = params['id']; // Extract ID from route params
      this.offerId = Number(id); // Convert ID to number
      if (this.offerId) {
        this.offerService.afficherOfferParId(this.offerId).subscribe(offer => {
          this.offerForm.patchValue(offer);
        });
      }
    });
    this.categorieService.afficherCategorie().subscribe(
      (categories) => {
        this.categories = categories;
      },
      (error) => {
        console.error('Failed to fetch categories', error);
      }
    );
  }

  onSubmit() {
    if(this.offerForm.valid && this.fileToUpload){
      this.fileService.uploadFile(this.fileToUpload).subscribe(
        response=>{
          console.log("File uploaded:",response);
          const newOffer:Offer={
            id:this.offerId ,...this.offerForm.value, dateDePublication: new Date(),image:this.fileToUpload?.name
          };
          console.log(newOffer);
          this.offerService.ajouterOffer(newOffer).subscribe(
            response=>{
              console.log("Offer added",response);
              this.router.navigate(['/offer/display']);
            }
          )
        }
      )
    }
  }

    
          

  onFileSelected(event:Event){
    const element =event.currentTarget as HTMLInputElement;
    let fileList:FileList | null =element.files;
    if(fileList){
      this.fileToUpload=fileList[0];
      this.offerForm.patchValue({image:this.fileToUpload.name});
      const reader=new FileReader();
      reader.onload=()=>{
        this.imagePreview=reader.result;
      }
      reader.readAsDataURL(this.fileToUpload);
    }
  }
}