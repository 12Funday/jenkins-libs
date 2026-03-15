def call(String repoUrl, String imageName) {
    // Checkout project repo
    git branch: 'main', url: repoUrl

    // Ambil commit ID terakhir
    def commitId = sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()

    // Cek kalau ada Git tag yang cocok dengan commit terakhir
    def gitTag = sh(script: "git describe --tags --exact-match || echo ''", returnStdout: true).trim()

    // Tentukan tag image
    def imageTag = gitTag ?: commitId
    echo "✅ Tag Docker image: ${imageTag}"

   // Retry build Docker sampai 3 kali jika gagal
    retry(3) {
        echo "🔄 build Docker..."
        sh "docker build -t ${imageName}:${imageTag} ."
    }

    // Optional: bisa juga build 'latest' tag
    sh "docker tag ${imageName}:${imageTag} ${imageName}:latest"

    // Run tests (contoh)
    sh "echo 'Running tests...'"
}