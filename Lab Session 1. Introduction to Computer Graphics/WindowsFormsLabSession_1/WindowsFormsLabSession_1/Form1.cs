using System.Drawing;
using System.Drawing.Drawing2D;
using System.Windows.Forms;

namespace WindowsFormsLabSession_1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            Graphics g = e.Graphics;
            Pen myDrawingPen = new Pen(Color.Blue);
            Pen myDrawingPen1 = new Pen(Color.Red);
            Pen myDrawingPen2 = new Pen(Color.Firebrick);
            Pen myDrawingPen3 = new Pen(Color.Green);
            Pen myDrawingPen4 = new Pen(Color.DarkOrchid);
            Pen myDrawingPen5 = new Pen(Color.CornflowerBlue);
            Pen myDrawingPen6 = new Pen(Color.Indigo);
            Pen myDrawingPen7 = new Pen(Color.DarkGoldenrod);
            Pen myDrawingPen8 = new Pen(Color.DarkOrange);

            // Form Division
            // |  |  |  |  |
            // -------------
            // |  |  |  |  |
            g.DrawLine(myDrawingPen, 0, Size.Height / 2, Size.Width, Size.Height / 2);
            g.DrawLine(myDrawingPen, Size.Width / 4, 0, Size.Width / 4, Size.Height);
            g.DrawLine(myDrawingPen, 2 * Size.Width / 4, 0, 2 * Size.Width / 4, Size.Height);
            g.DrawLine(myDrawingPen, 3 * Size.Width / 4, 0, 3 * Size.Width / 4, Size.Height);

            // Rectangle

            g.DrawRectangle(myDrawingPen1, 10, 10, (Size.Width / 4) - 20, (Size.Height / 2) - 20);

            // Triangle

            g.DrawLine(myDrawingPen2, (Size.Width / 4) + 10, (Size.Height / 2) - 10, 2 * (Size.Width / 4) - 10, (Size.Height / 2) - 10);
            g.DrawLine(myDrawingPen2, (Size.Width / 4) + 10, (Size.Height / 2) - 10, ((Size.Width / 4) / 2) + (Size.Width / 4), 10);
            g.DrawLine(myDrawingPen2, 2 * (Size.Width / 4) - 10, (Size.Height / 2) - 10, ((Size.Width / 4) / 2) + (Size.Width / 4), 10);

            // Circle

            g.DrawEllipse(myDrawingPen3, (Size.Width / 2) + 10, 10, (Size.Width / 4) - 20, (Size.Height / 2) - 20);

            // Rhomb

            Point[] points = new Point[4];
            points[0] = new Point((3 * (Size.Width / 4)) + Size.Width / 8, 10);
            points[1] = new Point(Size.Width - 20, (Size.Height / 4));
            points[2] = new Point((3 * (Size.Width / 4)) + Size.Width / 8, (Size.Height / 2) - 10);
            points[3] = new Point((Size.Width / 4) * 3 + 10, (Size.Height / 4));
            g.DrawPolygon(myDrawingPen4, points);


            // Trapeze

            GraphicsPath path = new GraphicsPath();
            path.AddLine(10, (Size.Height / 2) + 10, 10, Size.Height - 50);
            path.AddLine(10, Size.Height - 50,(Size.Width/4)-10, Size.Height-100);
            path.AddLine((Size.Width/4)-10, Size.Height-100, (Size.Width / 4) - 10, (Size.Height/2) + 50);
            path.AddLine((Size.Width / 4) - 10, (Size.Height/2) + 50, 10, (Size.Height / 2) + 10);
            g.DrawPath(myDrawingPen5, path);

            // Semicircle

            g.DrawArc(myDrawingPen6, (Size.Width/4)+10,(Size.Height/2)+10,(Size.Width/4)-20,(Size.Height/2)-60,90,180);
            g.DrawLine(myDrawingPen6, (Size.Width / 4)+ (Size.Width / 8), (Size.Height / 2) + 10, (Size.Width / 4) + (Size.Width / 8), Size.Height - 50);

            // Pie

            g.DrawPie(myDrawingPen7, (Size.Width / 2) + 10, (Size.Height / 2) + 10, (Size.Width / 4) - 20, (Size.Height / 2) - 60, 0, 270);

            // Line

            g.DrawLine(myDrawingPen8,((Size.Width*3)/4)+10, (Size.Height / 2) + 10, Size.Width-30,Size.Height-50);

            // Pixel
            Brush brush = new SolidBrush(Color.DarkOrange);
            g.FillRectangle(brush, Size.Width - 30, Size.Height/2 + 10, 1, 1);
        }

        private void Form1_Load(object sender, System.EventArgs e)
        {

        }
    }
}