import face_recognition
import cv2
import numpy as np

def convert_image_to_supported_type(image):
    """
    Convert the input image to an 8-bit grayscale or RGB image.

    Parameters:
    image (numpy.ndarray): Input image.

    Returns:
    numpy.ndarray: Converted image.
    """
    if image is None:
        raise ValueError("The input image is None.")
    
    # Convert the image to 8-bit grayscale if it is not already
    if len(image.shape) == 2:
        # If the image is already grayscale, just ensure it is 8-bit
        if image.dtype != np.uint8:
            image = image.astype(np.uint8)
    elif len(image.shape) == 3:
        # If the image is RGB or RGBA, ensure it is 8-bit
        if image.dtype != np.uint8:
            image = image.astype(np.uint8)
        # If the image is RGBA, convert it to RGB
        if image.shape[2] == 4:
            image = cv2.cvtColor(image, cv2.COLOR_RGBA2RGB)
        elif image.shape[2] != 3:
            raise RuntimeError("Unsupported image format, must be 8-bit gray or RGB.")
    else:
        raise RuntimeError("Unsupported image format, must be 8-bit gray or RGB.")

    return image

if __name__ == "__main__":
    print("Starting video capture")

    video_capture = cv2.VideoCapture(0)
    if not video_capture.isOpened():
        print("Error: Could not open video device.")
    else:
        print("Webcam initialized successfully.")

    # Load sample pictures and learn how to recognize them
    arij_image = face_recognition.load_image_file(r"C:\Users\User\test_face_rec\face_recognition\images\Arij.jpg")
    arij_face_encoding = face_recognition.face_encodings(arij_image)[0]

    sami_image = face_recognition.load_image_file(r"C:\Users\User\test_face_rec\face_recognition\images\sami.jpg")
    sami_face_encoding = face_recognition.face_encodings(sami_image)[0]

    # Create arrays of known face encodings and their names
    known_face_encodings = [
        arij_face_encoding,
        sami_face_encoding
    ]
    known_face_names = [
        "Arij",
        "sami"
    ]

    # Initialize some variables
    face_locations = []
    face_encodings = []
    face_names = []
    process_this_frame = True

    while True:
        # Grab a single frame of video
        ret, frame = video_capture.read()
        if not ret:
            print("Failed to grab frame")
            break

        # Only process every other frame of video to save time
        if process_this_frame:
            # Resize frame of video to 1/4 size for faster face recognition processing
            small_frame = cv2.resize(frame, (0, 0), fx=0.25, fy=0.25)

            # Convert the image from BGR color (which OpenCV uses) to RGB color (which face_recognition uses)
            rgb_small_frame = cv2.cvtColor(small_frame, cv2.COLOR_BGR2RGB)
            
            # Find all the faces and face encodings in the current frame of video
            face_locations = face_recognition.face_locations(rgb_small_frame)
            face_encodings = face_recognition.face_encodings(rgb_small_frame, face_locations)

            face_names = []
            for face_encoding in face_encodings:
                # See if the face is a match for the known face(s)
                matches = face_recognition.compare_faces(known_face_encodings, face_encoding)
                name = "Unknown"

                # Or instead, use the known face with the smallest distance to the new face
                face_distances = face_recognition.face_distance(known_face_encodings, face_encoding)
                best_match_index = np.argmin(face_distances)
                if matches[best_match_index]:
                    name = known_face_names[best_match_index]

                face_names.append(name)

        process_this_frame = not process_this_frame

        for (top, right, bottom, left), name in zip(face_locations, face_names):
            top *= 4
            right *= 4
            bottom *= 4
            left *= 4

            cv2.rectangle(frame, (left, top), (right, bottom), (0, 0, 255), 2)

            # Draw a label with a name below the face
            cv2.rectangle(frame, (left, bottom - 35), (right, bottom), (0, 0, 255), cv2.FILLED)
            font = cv2.FONT_HERSHEY_DUPLEX
            cv2.putText(frame, name, (left + 6, bottom - 6), font, 1.0, (255, 255, 255), 1)

        # Display the resulting image
        cv2.imshow('Video', frame)

        # Hit 'q' on the keyboard to quit!
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break

    # Release handle to the webcam
    video_capture.release()
    cv2.destroyAllWindows()
