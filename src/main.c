# include <stdio.h>
# include <GLFW/glfw3.h>
# include <glad.h>

static GLFWwindow* window;

int main( void )
{
    if (!glfwInit()) {
        fprintf(stderr, "[ main/ERROR ]: Unable to start GLFW!\n");
        return -1;
    }

    window = glfwCreateWindow(640, 480, "OpenCraft 25r01", NULL, NULL);
    if (!window) {
        glfwTerminate();
        return -1;
    }

    /* Make the window's context current */
    glfwMakeContextCurrent(window);

	glClearColor(1, 0, 0, 1);

    /* Loop until the user closes the window */
    while (!glfwWindowShouldClose(window))
    {
        /* Render here */
        glClear(GL_COLOR_BUFFER_BIT);

        /* Swap front and back buffers */
        glfwSwapBuffers(window);

        /* Poll for and process events */
        glfwPollEvents();
    }

    glfwDestroyWindow(window);
    glfwTerminate();
    return 0;
}
