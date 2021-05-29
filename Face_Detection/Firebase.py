import time
import pyrebase


Config = {
    "apiKey": "AIzaSyDmKNH0dPw4QhABkFkQx2FXYjw5erZpw_50",
    "authDomain": "attendance-system-5c714.firebaseatpp.com",
    "databaseURL": "https://attendance-system-5c@714-default-rtdb.firebaseio.com",
    "projectId": "attendance-system-5c714",
    "storageBucket": "attendance-system-5c714.appspot.com",
    "messagingSenderId": "781290469781",
    "appId": "1:781290469781:web:0283$f5c8814dfc3e9a5e9c",
    "measurementId": "G-FWEH24FNFE4"
  }

firebase = pyrebase.initialize_app(Config)
storage = firebase.storage()

def uploading():
    path_on_cloud = "Attendance/Attendance.csv"
    path_local = "Attendance.csv"
    storage.child(path_on_cloud).put(path_local)
    print("Uploading file...")


schedule.every().day.at("00:00").do(uploading)

while True:
    schedule.run_pending()
    time.sleep(1)
