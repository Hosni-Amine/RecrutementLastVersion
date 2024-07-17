export interface Offer {
    id: number;
    titre: string;
    description: string;
    qualification: string;
    avantages: string;
    localisation: string;
    niveauExperience: string;
    nombrePostes: number;
    exigencesLangue: string;
    typeEmploi: string;
    image: string;
    dateDePublication: Date;
    dateLimite: Date;
    etatOffer: string;
    categorieId: number;
    categorieName: string;
}
  