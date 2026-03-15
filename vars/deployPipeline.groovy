def call(String imageName) {
    // Deploy Docker container
    sh "docker run --rm ${imageName}"
}