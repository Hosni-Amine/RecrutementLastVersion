import cv2
import numpy as np
import face_recognition
import os
from fastapi import FastAPI, File, HTTPException, UploadFile
from fastapi.responses import JSONResponse
from fastapi.middleware.cors import CORSMiddleware

app = FastAPI()

images_path = os.path.join(os.path.dirname(__file__), './../upload')

known_face_encodings = []
known_face_names = []

for filename in os.listdir(images_path):
    if filename.endswith(('.jpg', '.jpeg', '.png')):
        image_path = os.path.join(images_path, filename)
        image = face_recognition.load_image_file(image_path)
        face_encodings = face_recognition.face_encodings(image)
        if face_encodings:
            known_face_encodings.append(face_encodings[0])
            known_face_names.append(os.path.splitext(filename)[0])

# Set up CORS
origins = [
    "http://localhost",
    "http://localhost:4200",
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.get("/")
async def root():
    return {"message": "Welcome to the face recognition API"}

def recognize_face(frame):
    rgb_frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
    
    face_locations = face_recognition.face_locations(rgb_frame)
    face_encodings = face_recognition.face_encodings(rgb_frame, face_locations)

    for face_encoding in face_encodings:
        matches = face_recognition.compare_faces(known_face_encodings, face_encoding)
        if any(matches):
            best_match_index = np.argmax(matches)
            name = known_face_names[best_match_index]
            return True, name
    
    return False, None

@app.post("/recognize_face")
async def recognize_face_api(file: UploadFile = File(...)):
    if not file.content_type.startswith('image/'):
        raise HTTPException(status_code=415, detail="Unsupported file type, must be an image")

    img = await file.read()
    nparr = np.fromstring(img, np.uint8)
    frame = cv2.imdecode(nparr, cv2.IMREAD_COLOR)

    success, name = recognize_face(frame)

    if success:
        return JSONResponse(content={"recognized": True, "name": name})
    else:
        return JSONResponse(content={"recognized": False})

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)
