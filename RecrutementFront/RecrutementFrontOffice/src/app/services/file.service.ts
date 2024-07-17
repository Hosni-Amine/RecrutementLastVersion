import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  private apiUrl='http://localhost:8082/api/files';

  constructor(private http:HttpClient) { }
  uploadFile(file:File):Observable<string>{
    const formData:FormData=new FormData();
    formData.append('file',file,file.name);
    return this.http.post(`${this.apiUrl}/upload`,formData,{
        reportProgress:true,
        responseType:'text'
    });
  }
}
