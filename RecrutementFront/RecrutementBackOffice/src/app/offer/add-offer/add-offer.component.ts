import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Offer } from 'src/app/models/offer.model';
import { FileService } from 'src/app/service/file.service';
import { OfferService } from 'src/app/service/offer.service';
import { CategorieService } from 'src/app/service/categorie.service';
import { Categorie } from 'src/app/models/categorie.model';

@Component({
  selector: 'app-add-offer',
  templateUrl: './add-offer.component.html',
  styleUrls: ['./add-offer.component.scss']
})
export class AddOfferComponent implements OnInit {
  offerForm: FormGroup;
  uploadProgress: number | null = null;
  imagePreview: string | ArrayBuffer | null = null;
  fileToUpload: File | null = null;
  categories: Categorie[] = [];

  constructor(
    private fb: FormBuilder,
    private fileService: FileService,
    private offerService: OfferService,
    private categorieService: CategorieService,
    private router: Router
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
      image: ['', Validators.required]
    });
  }

  ngOnInit() {
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
    if (this.offerForm.valid && this.fileToUpload) {
      this.fileService.uploadFile(this.fileToUpload).subscribe(
        (response) => {
          console.log('File uploaded:', response);
          const newOffer: Offer = {
            ...this.offerForm.value,
            image: this.fileToUpload?.name,
            dateDePublication: new Date()
          };
          this.offerService.ajouterOffer(newOffer).subscribe(
            (response) => {
              console.log('Offer added', response);
              this.router.navigate(['/offer/display']);
            },
            (error) => {
              console.error('Failed to add offer', error);
            }
          );
        },
        (error) => {
          console.error('Failed to upload file', error);
        }
      );
    }
  }

  onFileSelected(event: Event) {
    const element = event.currentTarget as HTMLInputElement;
    let fileList: FileList | null = element.files;
    if (fileList) {
      this.fileToUpload = fileList[0];
      this.offerForm.patchValue({ image: this.fileToUpload.name });
      const reader = new FileReader();
      reader.onload = () => {
        this.imagePreview = reader.result;
      };
      reader.readAsDataURL(this.fileToUpload);
    }
  }
}
