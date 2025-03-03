# include <stdio.h>
# include <GLFW/glfw3.h>

static GLFWwindow* window;

int main( void )
{
    if (!glfwInit())
    {
        printf("[ main/INFO ]: Unable to start GLFW!\n");
        return -1;
    }

    window = glfwCreateWindow(640, 480, "OpenCraft 25r01", NULL, NULL);
    if (!window)
    {
        glfwTerminate();
        return -1;
    }

    /* Make the window's context current */
    glfwMakeContextCurrent(window);

    /* Loop until the user closes the window */
    while (!glfwWindowShouldClose(window))
    {
        /* Render here */
        // We are going to use a custom library for the rendering because
        // we can reuse the same code for any OS and any rendering backend.
        // It could work with OpenGL(ES),DirectX,Vulkan or something like bgfx (C port)

        // ~~~~~ glClear(GL_COLOR_BUFFER_BIT); ~~~~~

        /* Swap front and back buffers */
        glfwSwapBuffers(window);

        /* Poll for and process events */
        glfwPollEvents();
    }

    glfwDestroyWindow(window);
    glfwTerminate();
    return 0;
}
