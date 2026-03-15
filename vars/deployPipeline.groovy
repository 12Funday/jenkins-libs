def call(String imageName) {
    // Deploy Docker container
    sh "docker run -d -p 5000:5000 ${imageName}"
}